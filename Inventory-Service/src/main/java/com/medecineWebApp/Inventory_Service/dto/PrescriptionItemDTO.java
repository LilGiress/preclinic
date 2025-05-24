package com.medecineWebApp.Inventory_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionItemDTO extends AuditableDTO {
    private Long id;

    private PrescriptionDTO prescription;

    private MedicalConsumableDTO consumable; // Médicament prescrit

    private int quantity;
    private String dosageInstructions;
}
