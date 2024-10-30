package com.medecineWebApp.patients.payload.request;

import com.medecineWebApp.patients.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PatientRequest {
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
}
