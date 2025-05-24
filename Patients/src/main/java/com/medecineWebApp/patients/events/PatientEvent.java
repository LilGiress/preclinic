package com.medecineWebApp.patients.events;

import com.medecineWebApp.patients.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Set<Long> roles ;
}
