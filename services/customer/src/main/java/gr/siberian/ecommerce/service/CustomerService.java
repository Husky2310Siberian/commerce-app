package gr.siberian.ecommerce.service;

import gr.siberian.ecommerce.domain.Customer;
import gr.siberian.ecommerce.domain.CustomerRequest;
import gr.siberian.ecommerce.exceptions.CustomerNotFoundException;
import gr.siberian.ecommerce.mapper.CustomerMapper;
import gr.siberian.ecommerce.repository.ICustomerRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor

public class CustomerService {

    private final ICustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     *
     * @param request
     * @return
     */
    public String createCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Can not update customer:: No customer found with id :: %s", request.id())
                ));

        mergeCustomer(customer , request);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if (request.address() != null){
            customer.setAddress(request.address());
        }
    }
}
