package com.medecineWebApp.Configuration.payload.request;

import com.medecineWebApp.Configuration.models.role.Permission;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolesRequest {
    private String name;
    private List<Permission> permissions;
}
