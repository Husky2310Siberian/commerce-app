package gr.siberian.ecommerce.notification.repository;

import gr.siberian.ecommerce.notification.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface INotificationRepository extends MongoRepository<Notification, String> {
}
