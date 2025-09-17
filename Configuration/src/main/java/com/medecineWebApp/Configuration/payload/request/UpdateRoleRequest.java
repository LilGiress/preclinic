package com.medecineWebApp.Configuration.payload.request;

import com.medecineWebApp.Configuration.models.role.Permission;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {
    private String name;
    private List<Permission> permissions;
}
