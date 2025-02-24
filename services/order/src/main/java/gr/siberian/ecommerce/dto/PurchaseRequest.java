package gr.siberian.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record PurchaseRequest(

    @NotNull(message = "Product is mandatory")
    Integer productId,

    @NotNull(message = "Quantity is mandatory")
    double quantity
    )
{

}
