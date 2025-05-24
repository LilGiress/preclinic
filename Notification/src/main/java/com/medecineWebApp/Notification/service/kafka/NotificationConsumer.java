package com.medecineWebApp.Notification.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medecineWebApp.Notification.models.Notification;
import com.medecineWebApp.Notification.service.NotificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class NotificationConsumer {
    private final ObjectMapper mapper = new ObjectMapper();
    private final NotificationService notificationService;

    public NotificationConsumer( NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "global-notifications", groupId = "global-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        try {
            log.info(message);
            Notification notification = mapper.readValue(message, Notification.class);
            notificationService.processNotification(notification);
        }catch (JsonProcessingException e){
            log.error(e);
            System.err.println("Erreur de parsing JSON : " + e.getMessage());
        }

    }

}
