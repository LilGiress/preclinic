package com.medecineWebApp.Employees.models;


import com.medecineWebApp.Employees.enums.TreatmentStatus;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Treatment  {

    private Long id;
    private String description;
    private Patient patient;
    private LocalDate startDate;
    private LocalDate endDate;
    private TreatmentStatus status;
    private Doctor doctor;
}
