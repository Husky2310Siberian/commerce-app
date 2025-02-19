package gr.siberian.ecommerce.dto;

import gr.siberian.ecommerce.domain.Address;

public record CustomerResponse (
        String id,

        String firstname,

        String lastname,

        String email,

        Address address
    )
{

}
