package gr.siberian.ecommerce.controller;

import gr.siberian.ecommerce.domain.CustomerRequest;
import gr.siberian.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     *
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequest request){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerRequest request ){
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }
}
