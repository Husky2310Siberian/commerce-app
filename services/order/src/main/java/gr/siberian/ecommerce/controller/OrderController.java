package gr.siberian.ecommerce.controller;

import gr.siberian.ecommerce.dto.OrderRequest;
import gr.siberian.ecommerce.dto.OrderResponse;
import gr.siberian.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@Valid @RequestBody OrderRequest request){
        return ResponseEntity.ok(orderService.createdOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable ("order-id") Integer orderId){
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }
}
