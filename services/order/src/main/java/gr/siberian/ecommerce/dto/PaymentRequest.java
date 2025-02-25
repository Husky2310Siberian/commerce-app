package gr.siberian.ecommerce.dto;

import gr.siberian.ecommerce.customerproxy.CustomerResponse;
import gr.siberian.ecommerce.paymethods.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

                             BigDecimal amount,
                             PaymentMethod paymentMethod,
                             Integer orderId,
                             String orderReference,
                             CustomerResponse customer
) {
}
