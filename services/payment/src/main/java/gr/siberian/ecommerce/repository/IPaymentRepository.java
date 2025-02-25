package gr.siberian.ecommerce.repository;

import gr.siberian.ecommerce.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment , Integer> {

}
