package gr.siberian.ecommerce.repository;

import gr.siberian.ecommerce.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product , Integer> {

    List<Product> findAllProductsByIdInOrderById(List<Integer> productsId);

}
