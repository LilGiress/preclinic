package com.medecineWebApp.Notification.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO extends AuditableDTO{
    private Long id;
    private String message;
    private String type; // ex: TASK_ADDED, TASK_UPDATED
    private Long userId; // Utilisateur concerné
    private LocalDateTime createdAt;
    private boolean read = false;
}
