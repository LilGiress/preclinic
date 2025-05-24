package com.medecineWebApp.patients.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medecineWebApp.patients.enums.TreatmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "patients_treatment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Treatment extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "patient_id")
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
