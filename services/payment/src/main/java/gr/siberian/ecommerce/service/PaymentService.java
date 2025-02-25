package gr.siberian.ecommerce.service;

import gr.siberian.ecommerce.dto.PaymentNotificationRequest;
import gr.siberian.ecommerce.dto.PaymentRequest;
import gr.siberian.ecommerce.mapper.PaymentMapper;
import gr.siberian.ecommerce.notification.NotificationProducer;
import gr.siberian.ecommerce.repository.IPaymentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final IPaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(@Valid PaymentRequest request) {
        var payment = paymentRepository.save(paymentMapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
