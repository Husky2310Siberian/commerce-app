package gr.siberian.ecommerce.mapper;

import gr.siberian.ecommerce.domain.Payment;
import gr.siberian.ecommerce.dto.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .build();
    }
}
