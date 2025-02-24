package gr.siberian.ecommerce.mapper;

import gr.siberian.ecommerce.domain.order.Order;
import gr.siberian.ecommerce.dto.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        return  Order.builder()
                .id(request.id())
                .CustomerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }
}
