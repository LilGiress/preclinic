package com.medecineWebApp.Configuration.payload.request;

import com.medecineWebApp.Configuration.models.role.ActionPermission;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequest {
    private String label;
    private String description;
    private List<ActionPermissionRequest> actions = new ArrayList<>();
}
