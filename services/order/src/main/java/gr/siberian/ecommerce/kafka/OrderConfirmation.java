package gr.siberian.ecommerce.kafka;

import gr.siberian.ecommerce.customerproxy.CustomerResponse;
import gr.siberian.ecommerce.dto.PurchaseResponse;
import gr.siberian.ecommerce.paymethods.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        CustomerResponse customerResponse,

        List<PurchaseResponse> products

) {
}
