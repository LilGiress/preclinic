package com.medecineWebApp.Inventory_Service.models.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent {
    private String message;
    private String type;
    private String userName;
    private String userEmail;
}
