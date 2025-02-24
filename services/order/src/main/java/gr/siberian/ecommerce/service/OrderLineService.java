package gr.siberian.ecommerce.service;

import gr.siberian.ecommerce.domain.orderline.OrderLine;
import gr.siberian.ecommerce.dto.OrderLineRequest;
import gr.siberian.ecommerce.mapper.OrderLineMapper;
import gr.siberian.ecommerce.repository.IOrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final IOrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }
}
