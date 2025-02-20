package gr.siberian.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(Integer id,

                             @NotNull(message = "Product name is a required field")
                             String name,

                             @NotNull(message = "Product description is a required field")
                             String description,

                             @Positive(message = "Quantity should be positive")
                             double availableQuantity,

                             @Positive(message = "Price should be positive")
                             BigDecimal price,

                             @NotNull(message = "Product category is a required field")
                             Integer categoryId) {
}
