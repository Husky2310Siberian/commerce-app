package gr.siberian.ecommerce.dto;

import gr.siberian.ecommerce.domain.PaymentMethod;
import java.math.BigDecimal;

public record PaymentNotificationRequest(

        String orderReference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerFirstname,

        String customerLastname,

        String customerEmail
) {
}
