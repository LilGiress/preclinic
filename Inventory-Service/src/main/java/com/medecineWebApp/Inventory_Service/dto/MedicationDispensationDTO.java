package com.medecineWebApp.Inventory_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDispensationDTO extends AuditableDTO {
    private Long id;

    private Long patientId;
    private Long doctorId;

    private MedicalConsumableDTO consumable;

    private int quantityDispensed;
    private LocalDateTime dispensedAt;
}
