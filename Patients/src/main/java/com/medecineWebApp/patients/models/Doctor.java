package com.medecineWebApp.patients.models;


import com.medecineWebApp.patients.enums.Gender;
import com.medecineWebApp.patients.enums.PatientStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor  {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private LocalDate doctorBirthday;
    private String imageUrl;
    private String biography;
    private String doctorPostalCode;
    private String doctorCountry;
    private PatientStatus status;
    private Gender gender;
    private String address;
    private LocalDateTime dateOfCreation;
    private LocalDateTime lastModifiedDate;
}
