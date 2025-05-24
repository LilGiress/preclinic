package com.medecineWebApp.Employees.models;

import com.medecineWebApp.Employees.models.doctors.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "medical_record")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MedicalRecord extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date date;
    
    private Long patientId;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    private Long treatmentId;
    private Long serviceId;
    
}
