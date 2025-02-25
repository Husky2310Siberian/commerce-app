package gr.siberian.ecommerce.notification.domain;

import gr.siberian.ecommerce.notification.confirm.OrderConfirmation;
import gr.siberian.ecommerce.notification.enumtypes.NotificationType;
import gr.siberian.ecommerce.notification.kafka.order.OrderConfirmation;
import gr.siberian.ecommerce.notification.kafka.payment.PaymentConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;

    private NotificationType notificationType;

    private LocalDateTime notificationDate;

    private gr.siberian.ecommerce.notification.kafka.order.OrderConfirmation orderConfirmation;

    private PaymentConfirmation paymentConfirmation;

}
