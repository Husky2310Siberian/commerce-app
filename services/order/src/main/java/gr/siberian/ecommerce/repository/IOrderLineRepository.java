package gr.siberian.ecommerce.repository;

import gr.siberian.ecommerce.domain.orderline.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderLineRepository extends JpaRepository<OrderLine , Integer> {
}
