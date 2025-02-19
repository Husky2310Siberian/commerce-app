package gr.siberian.ecommerce.service;

import gr.siberian.ecommerce.domain.Customer;
import gr.siberian.ecommerce.dto.CustomerRequest;
import gr.siberian.ecommerce.dto.CustomerResponse;
import gr.siberian.ecommerce.exceptions.CustomerNotFoundException;
import gr.siberian.ecommerce.mapper.CustomerMapper;
import gr.siberian.ecommerce.repository.ICustomerRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().
                stream().
                map(customerMapper::fromCustomer).
                collect(Collectors.toList());
    }

    /**
     *
     * @param customerId
     * @return true --> customer exist
     * @return false --> customer does not exist
     */
    public Boolean existsCustomerById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    /**
     *
     * @param customerId
     * @return
     */
    public CustomerResponse findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(format(
                        "No customer found with the provided id: %s" ,customerId))
                );
    }

    public CustomerResponse findCustomerByName(String customerName) {
        return customerRepository.findCustomerByName(customerName)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(format(
                        "No customer found with the provided lastname: %s" ,customerName))
                );
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }


}
