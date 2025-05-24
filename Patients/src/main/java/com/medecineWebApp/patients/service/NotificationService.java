package com.medecineWebApp.patients.service;

import com.medecineWebApp.patients.models.NotificationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public NotificationService(KafkaTemplate<String, NotificationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationEvent event) {
        kafkaTemplate.send("notification-topic", event);
        System.out.println("📤 Notification envoyée à Kafka : " + event.getMessage());
    }

}
