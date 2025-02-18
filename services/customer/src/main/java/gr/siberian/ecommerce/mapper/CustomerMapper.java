package gr.siberian.ecommerce.mapper;

import gr.siberian.ecommerce.domain.Customer;
import gr.siberian.ecommerce.domain.CustomerRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    /**
     * Takes a customer request and converts to Customer
     * @param request is a CustomerRequest
     * @return a Customer
     */
    public Customer toCustomer(CustomerRequest request) {
        if(request == null){
            return null;
        }

        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }
}
