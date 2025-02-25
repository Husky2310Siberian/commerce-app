package gr.siberian.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,

        @NotNull(message = "Required field")
        String firstname,

        @NotNull(message = "Required field")
        String lastname,

        @NotNull(message = "Required field")
        @Email(message = "The customer is not correctly formatted")
        String email

) {
}
