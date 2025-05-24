package com.medecineWebApp.Configuration.service.kafka;

import com.medecineWebApp.Configuration.models.kafka.NotificationEvent;
import com.medecineWebApp.Configuration.models.kafka.PasswordResetEvent;
import com.medecineWebApp.Configuration.models.kafka.UserEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final String TOPIC = "notification-topic";
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate1;
    private final KafkaTemplate<String, PasswordResetEvent> kafkaTemplate2;

    public NotificationService(KafkaTemplate<String, NotificationEvent> kafkaTemplate, KafkaTemplate<String, UserEvent> kafkaTemplate1, KafkaTemplate<String, PasswordResetEvent> kafkaTemplate2) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplate1 = kafkaTemplate1;
        this.kafkaTemplate2 = kafkaTemplate2;
    }

    public void sendNotification( NotificationEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("📤 Notification envoyée à Kafka : " + event.getMessage());
    }

    public void sendWelcomeNotification( UserEvent userEvent) {
        kafkaTemplate1.send("user-created-topic", userEvent);

    }

    public void sendPasswordResetNotification( PasswordResetEvent passwordResetEvent) {
        kafkaTemplate2.send("password-reset-topic", passwordResetEvent);
    }
}
