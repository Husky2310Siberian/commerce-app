package gr.siberian.ecommerce.repository;

import gr.siberian.ecommerce.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product , Integer> {

}
