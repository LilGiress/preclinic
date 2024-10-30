package com.medecineWebApp.Configuration.payload.response;


import com.medecineWebApp.Configuration.enums.RoleType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean enabled;
    private boolean accountLocked;
    private RoleType roles;
}
