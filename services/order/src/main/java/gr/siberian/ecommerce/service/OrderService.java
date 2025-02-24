package gr.siberian.ecommerce.service;

import gr.siberian.ecommerce.customerproxy.CustomerProxy;
import gr.siberian.ecommerce.dto.OrderLineRequest;
import gr.siberian.ecommerce.dto.OrderRequest;
import gr.siberian.ecommerce.dto.PurchaseRequest;
import gr.siberian.ecommerce.exceptions.BusinessException;
import gr.siberian.ecommerce.mapper.OrderMapper;
import gr.siberian.ecommerce.productproxy.ProductProxy;
import gr.siberian.ecommerce.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerProxy customerProxy;
    private final ProductProxy productProxy;
    private final IOrderRepository IOrderRepository;
    private final OrderMapper mapperOrder;
    private final OrderLineService orderLineService;

    public Integer createdOrder(OrderRequest request) {

        //check customer if exists --> OpenFeign
        var customer = this.customerProxy.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Can not create order, no customer exists"));


        //purchase products --> product microservice (RestTemplate)
        this.productProxy.purchaseProducts(request.products());

        //persist order
        var order = IOrderRepository.save(mapperOrder.toOrder(request));

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

        //start payment process

        //send order confirmation --> notification microservice

        return null;
    }
}
