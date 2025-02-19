package gr.siberian.ecommerce.mapper;

import gr.siberian.ecommerce.domain.Customer;
import gr.siberian.ecommerce.dto.CustomerRequest;
import gr.siberian.ecommerce.dto.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    /**
     * Takes a CustomerRequest and converts to Customer
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

    /**
     * Takes a customer and converts to Customer Response
     * @param customer
     * @return Customer Response
     */
    public CustomerResponse fromCustomer(Customer customer) {
//         if (customer == null) {
//             return null;
//         }
         return new CustomerResponse(
                 customer.getId(),
                 customer.getFirstname(),
                 customer.getLastname(),
                 customer.getEmail(),
                 customer.getAddress()
         );
    }
}
