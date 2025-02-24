package gr.siberian.ecommerce.service;

import gr.siberian.ecommerce.dto.ProductPurchaseRequest;
import gr.siberian.ecommerce.dto.ProductPurchaseResponse;
import gr.siberian.ecommerce.dto.ProductRequest;
import gr.siberian.ecommerce.dto.ProductResponse;
import gr.siberian.ecommerce.exception.ProductPurchaseException;
import gr.siberian.ecommerce.mapper.ProductMapper;
import gr.siberian.ecommerce.repository.IProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest req) {
        var product = productMapper.toProduct(req);
        return productRepository.save(product).getId();
    }

    /**
     * Process a list of product purchase requests,
     * validate their availability
     * and update stock quantities accordingly
     * @param req
     * @return
     */
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> req) {

        // receives a list of ProductPurchaseRequest req, where each request contains a product ID and a quantity.
        // extracts all product IDs from the request list and stores them in productsId
        var productsId = req
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        // we find the products that are stored in warehouse
        // retrieves all products that match the given IDs
        var storedProducts = productRepository.findAllProductsByIdInOrderById(productsId);

        //If the number of retrieved products (storedProducts.size()) is less than the requested product count (productsId.size()),
        // it means one or more requested products do not exist in the database.
        if (productsId.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more products, does not exist");
        }

        //sorts the original request list (req) based on product ID.
        var storedRequest = req
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID: " + productRequest.productId());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toproductPurchaseResponse(product , productRequest.quantity()));
        }
        return purchasedProducts;
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
