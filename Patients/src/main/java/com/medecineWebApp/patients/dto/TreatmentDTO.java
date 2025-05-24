package com.medecineWebApp.patients.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medecineWebApp.patients.enums.TreatmentStatus;
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
public class TreatmentDTO extends AuditableDTO {
    private Long id;
    private String description;

    private Patient patient;

    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private TreatmentStatus status;

    private Long doctorId;

    @JsonIgnore
    @Column(nullable = false)
    private Long appointmentId;  // Stocker l'ID de l'appointment au lieu de l'objet complet
    @Column(nullable = false)
    private Long medicalRecordId; // Stocker l'ID du medical record

}
