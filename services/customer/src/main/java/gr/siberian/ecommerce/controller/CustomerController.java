package gr.siberian.ecommerce.controller;

import gr.siberian.ecommerce.dto.CustomerRequest;
import gr.siberian.ecommerce.dto.CustomerResponse;
import gr.siberian.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     *
     * @param request
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerRequest request ){
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    /**
     * Check with true or false, if a customer exists or not, using ID
     * @return true or false
     */
    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id")
                                                  String customerId){
        return ResponseEntity.ok(customerService.existsCustomerById(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id")
                                              String customerId){
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @GetMapping("/{customer-name}")
    public ResponseEntity<CustomerResponse> findByName(@PathVariable("customer-name")
                                                     String customerName){
        return ResponseEntity.ok(customerService.findCustomerByName(customerName));
    }

    @DeleteMapping("{customer-id}")
    public ResponseEntity<Void> deleteById(@PathVariable("customer-id")
                                                   String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
