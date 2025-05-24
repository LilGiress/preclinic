package com.medecineWebApp.Configuration.payload.request;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {
    private String name;
    private Set<UpdatePermissionRequest> permissions;
}
