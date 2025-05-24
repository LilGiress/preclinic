package com.medecineWebApp.Configuration.events;


import com.medecineWebApp.Configuration.enums.EventType;
import com.medecineWebApp.Configuration.models.role.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientEvent {
    private Long id;
    private Long userId;
    private String firstname;
    private String lastname;
    private EventType eventType;
    private String email;
    private String password;
    private Set<Long> roles = new HashSet<>();
}
