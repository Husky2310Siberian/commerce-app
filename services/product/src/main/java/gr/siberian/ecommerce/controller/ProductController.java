package gr.siberian.ecommerce.controller;

import gr.siberian.ecommerce.dto.ProductPurchaseRequest;
import gr.siberian.ecommerce.dto.ProductPurchaseResponse;
import gr.siberian.ecommerce.dto.ProductRequest;
import gr.siberian.ecommerce.dto.ProductResponse;
import gr.siberian.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    /**
     * This endpoint allows the creation of a new product.
     * @param req
     * @return
     */
    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest req) {
        return ResponseEntity.ok(productService.createProduct(req));
    }

    /**
     * This endpoint allows the purchase of multiple products.
     * @param req
     * @return
     */
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> req){
        return ResponseEntity.ok(productService.purchaseProduct(req));
    }

    /**
     * This endpoint allows the find a product by ID.
     * @param productId
     * @return
     */
    @GetMapping("/{product-id}")
    public  ResponseEntity<ProductResponse> findById(@PathVariable("product-id") Integer productId){
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    /**
     * This endpoint allows the find all products
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll () {
        return ResponseEntity.ok(productService.findAllProducts());
    }


}
