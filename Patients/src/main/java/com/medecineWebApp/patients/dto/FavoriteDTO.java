package com.medecineWebApp.patients.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDTO extends AuditableDTO {
    private Long id;
    private Long patientId; // Patient ID from PatientService
    private Long doctorId;
}
