package com.medecineWebApp.patients.dto;

import com.medecineWebApp.patients.enums.TreatmentStatus;
import com.medecineWebApp.patients.models.Doctor;
import com.medecineWebApp.patients.models.Patient;
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
public class TreatmentDTO {
    private Long id;
    private String description;
    private Patient patient;
    private Doctor doctorId;
    private LocalDate startDate;
    private LocalDate endDate;

    private TreatmentStatus status;
}
