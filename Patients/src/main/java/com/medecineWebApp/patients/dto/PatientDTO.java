package com.medecineWebApp.patients.dto;

import com.medecineWebApp.patients.enums.Gender;
import com.medecineWebApp.patients.enums.PatientStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Gender gender;
    private LocalDate birthDate;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private PatientStatus status;
    private String ImageUrl;
    private LocalDateTime dateOfCreation;
    private LocalDateTime lastModifiedDate;
}
