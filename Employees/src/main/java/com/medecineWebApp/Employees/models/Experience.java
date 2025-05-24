package com.medecineWebApp.Employees.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "experience")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Experience extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hospitalName;
    private String designation;
    private String fromDate;
    private String toDate;
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonManagedReference
    private Doctor profile;
}
