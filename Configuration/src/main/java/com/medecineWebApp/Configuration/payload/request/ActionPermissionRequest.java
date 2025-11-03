package com.medecineWebApp.Configuration.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActionPermissionRequest {
    private String label;       // Exemple: "CAN_READ", "CAN_WRITE"
    private boolean selected;
}
