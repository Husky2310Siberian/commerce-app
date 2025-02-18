package gr.siberian.ecommerce.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest (
         String id,

         @NotNull(message = "Customer firstname is a required field")
         String firstname,

         @NotNull(message = "Customer lastname is a required field")
         String lastname,

         @NotNull(message = "Customer email is a required field")
         @Email(message = "Customer email is not valid")
         String email,

         Address address
        )
{

        }
