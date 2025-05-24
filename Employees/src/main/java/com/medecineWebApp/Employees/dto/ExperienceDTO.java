package com.medecineWebApp.Employees.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDTO extends AuditableDTO{
    private Long id;

    private String hospitalName;
    private String designation;
    private String fromDate;
    private String toDate;

    @JsonManagedReference
    private DoctorDTO profile;
}
