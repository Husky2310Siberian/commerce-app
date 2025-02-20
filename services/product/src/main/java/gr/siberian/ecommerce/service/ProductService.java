package gr.siberian.ecommerce.service;

import gr.siberian.ecommerce.dto.ProductPurchaseRequest;
import gr.siberian.ecommerce.dto.ProductPurchaseResponse;
import gr.siberian.ecommerce.dto.ProductRequest;
import gr.siberian.ecommerce.dto.ProductResponse;
import gr.siberian.ecommerce.mapper.ProductMapper;
import gr.siberian.ecommerce.repository.IProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(@Valid ProductRequest req) {
        var product = productMapper.toProduct(req);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> req) {
        return null;
    }

    public ProductResponse findProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
