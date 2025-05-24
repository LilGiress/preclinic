package com.medecineWebApp.Asset.Management.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceNotificationEvent {
    private String recipientId; // ID du destinataire
    private String message;
    private String eventType; // "MAINTENANCE_ALERT", "ASSET_ASSIGNED", etc.
}
