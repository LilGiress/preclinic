package com.medecineWebApp.Configuration.models;

import com.medecineWebApp.Configuration.enums.TreatmentStatus;
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

public class Treatment {
    private Long id;
    private String description;
    private Patient patient;
    private Doctor doctorId;
    private LocalDate startDate;
    private LocalDate endDate;
    private TreatmentStatus status;
}
