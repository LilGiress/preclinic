package com.medecineWebApp.Employees.models;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Patient implements Serializable {
    private Long id;
    private String phone;
    private String gender;
    private String birthDate;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String status;
    private String ImageUrl;


    private LocalDateTime dateOfCreation;
    private LocalDateTime lastModifiedDate;

}
