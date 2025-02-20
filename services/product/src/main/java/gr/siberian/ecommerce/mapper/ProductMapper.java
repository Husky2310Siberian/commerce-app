package gr.siberian.ecommerce.mapper;

import gr.siberian.ecommerce.dto.ProductRequest;
import gr.siberian.ecommerce.dto.ProductResponse;
import gr.siberian.ecommerce.product.domain.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest req) {
        return Product
                .builder()
                .id(req.id())
                .name(req.name())
                .description(req.description())
                .price(req.price())
                .availableQuantity(req.availableQuantity())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
                );
    }
}
