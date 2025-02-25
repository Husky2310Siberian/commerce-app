package gr.siberian.ecommerce.mapper;

import gr.siberian.ecommerce.domain.order.Order;
import gr.siberian.ecommerce.domain.orderline.OrderLine;
import gr.siberian.ecommerce.dto.OrderLineRequest;
import gr.siberian.ecommerce.dto.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                        .id(orderLineRequest.id())
                        .build()
                )
                .productId(orderLineRequest.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
