package gr.siberian.ecommerce.dto;

import gr.siberian.ecommerce.paymethods.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,

        @NotNull(message = "Order amount should be positive")
        BigDecimal amount,

        String reference,

        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        String customerId,

        @NotEmpty(message = "Customers list should not be empty")
        List<PurchaseRequest> products

) {
}
