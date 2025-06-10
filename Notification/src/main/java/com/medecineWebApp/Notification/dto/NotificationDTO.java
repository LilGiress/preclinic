package com.medecineWebApp.Notification.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO{
    private Long id;
    private String message;
    private String type; // ex: TASK_ADDED, TASK_UPDATED
    private String userName; // Utilisateur concerné
    private String userEmail;
    private LocalDateTime createdAt;
    private boolean read = false;
    private boolean sent;
}
