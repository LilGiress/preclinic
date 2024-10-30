package com.medecineWebApp.patients.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private Long id;
    private String name;
    private List<Address> addresses;
    private LocalDateTime dateOfCreation;
    private LocalDateTime lastModifiedDate;


}
