package com.medecineWebApp.doctors.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Long id;
    private String street;
    private String city;
    private String postalCode;
    private Country country;
    private LocalDateTime dateOfCreation;
    private LocalDateTime lastModifiedDate;
}
