package com.medecineWebApp.Configuration.models.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {
    private String email;
    private String username;
    private String password;
    private String token;
    private String activationLink;
}
