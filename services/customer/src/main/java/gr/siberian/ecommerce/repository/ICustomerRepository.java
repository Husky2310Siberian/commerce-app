package gr.siberian.ecommerce.repository;

import gr.siberian.ecommerce.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICustomerRepository extends MongoRepository<Customer, String> {
}
