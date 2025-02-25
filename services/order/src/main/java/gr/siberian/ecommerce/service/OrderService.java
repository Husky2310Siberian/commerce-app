package gr.siberian.ecommerce.service;

import gr.siberian.ecommerce.customerproxy.CustomerProxy;
import gr.siberian.ecommerce.dto.*;
import gr.siberian.ecommerce.exceptions.BusinessException;
import gr.siberian.ecommerce.kafka.OrderConfirmation;
import gr.siberian.ecommerce.kafka.OrderProducer;
import gr.siberian.ecommerce.mapper.OrderMapper;
import gr.siberian.ecommerce.payment.IPaymentProxy;
import gr.siberian.ecommerce.productproxy.ProductProxy;
import gr.siberian.ecommerce.repository.IOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerProxy customerProxy;
    private final ProductProxy productProxy;
    private final IOrderRepository orderRepository;
    private final OrderMapper mapperOrder;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final IPaymentProxy paymentProxy;

    @Transactional
    public Integer createdOrder(OrderRequest request) {

        //check customer if exists --> OpenFeign
        var customer = this.customerProxy.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Can not create order, no customer exists"));


        //purchase products --> product microservice (RestTemplate)
        var purchasedProducts = this.productProxy.purchaseProducts(request.products());

        //persist order
        var order = orderRepository.save(mapperOrder.toOrder(request));

        //persist order lines
        for (PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
                );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentProxy.requestOrderPayment(paymentRequest);

        //send order confirmation --> notification microservice
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapperOrder::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapperOrder::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided id: %d" , orderId)));
    }
}
