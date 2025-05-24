package com.medecineWebApp.Employees.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDTO extends AuditableDTO{
    private Long id;
    private String description;
    private Date date;
    private Long patientId;
    private DoctorDTO doctor;
    private Long treatmentId;
    private Long serviceId;
}
