package gr.siberian.ecommerce.repository;

import gr.siberian.ecommerce.domain.orderline.OrderLine;
import gr.siberian.ecommerce.dto.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderLineRepository extends JpaRepository<OrderLine , Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
