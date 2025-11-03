package com.medecineWebApp.Configuration.payload.request;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medecineWebApp.Configuration.models.role.Permission;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolesRequest {
    private String name;
    private String description;
    private List<PermissionRequest> permissions = new ArrayList<>();
}
