package com.medecineWebApp.Asset.Management.utilis;

import com.medecineWebApp.Asset.Management.kafka.MaintenanceNotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaNotificationProducer {
    private final KafkaTemplate<String, MaintenanceNotificationEvent> kafkaTemplate;
    @Autowired
    public KafkaNotificationProducer(KafkaTemplate<String, MaintenanceNotificationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public  void sendNotification(MaintenanceNotificationEvent event) {
        kafkaTemplate.send("notification-topic", event);
        System.out.println("📢 Notification envoyée via Kafka : " + event.getMessage());
    }
}
