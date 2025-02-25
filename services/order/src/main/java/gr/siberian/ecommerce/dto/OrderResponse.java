package gr.siberian.ecommerce.dto;

import gr.siberian.ecommerce.paymethods.PaymentMethod;
import java.math.BigDecimal;

public record OrderResponse(
        Integer id,

        String reference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerId
) {
}
