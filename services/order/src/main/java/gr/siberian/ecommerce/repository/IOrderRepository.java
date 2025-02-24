package gr.siberian.ecommerce.repository;

import gr.siberian.ecommerce.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
}
