package gr.siberian.ecommerce.customerproxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Allows to application to communicate
 * with external service "customer-service" via HTTP requests.
 */

@FeignClient(
                name = "customer-service" ,
                url = "${application.config.customer-url}"
)
public interface CustomerProxy {

    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}
