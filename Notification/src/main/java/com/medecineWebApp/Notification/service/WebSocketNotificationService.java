package com.medecineWebApp.Notification.service;

import com.medecineWebApp.Notification.models.Notification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketNotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendRealTimeNotification(Notification notification) {
        // Envoie sur le topic /topic/notifications
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }
}
