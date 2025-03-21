package gr.siberian.ecommerce.notification.kafka.consumer;

import gr.siberian.ecommerce.notification.domain.Notification;
import gr.siberian.ecommerce.notification.kafka.order.OrderConfirmation;
import gr.siberian.ecommerce.notification.kafka.payment.PaymentConfirmation;
import gr.siberian.ecommerce.notification.repository.INotificationRepository;
import gr.siberian.ecommerce.notification.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import static gr.siberian.ecommerce.notification.enumtypes.NotificationType.ORDER_CONFIRMATION;
import static gr.siberian.ecommerce.notification.enumtypes.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final INotificationRepository notificationRepository;
    private final EmailService emailService;


    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        //send an email
        var customerName = paymentConfirmation.customerFirstname() + "" + paymentConfirmation.customerLastname();
        emailService.sentEmailForSuccessPayment(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .notificationType(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        //send an email
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().firstname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
