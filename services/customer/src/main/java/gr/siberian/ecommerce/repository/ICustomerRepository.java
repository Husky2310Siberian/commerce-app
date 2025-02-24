package gr.siberian.ecommerce.repository;

import gr.siberian.ecommerce.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ICustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findCustomerName(String customerName);
}


