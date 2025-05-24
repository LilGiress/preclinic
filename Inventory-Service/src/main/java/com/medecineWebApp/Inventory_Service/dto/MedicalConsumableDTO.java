package com.medecineWebApp.Inventory_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalConsumableDTO extends AuditableDTO{
    private Long id;
    private String name;
    private String category; // Médicament, seringue, gants, etc.
    private String unit; // ml, mg, unité
    private String manufacturer;
    private SupplierDTO supplier;
    private LocalDate expirationDate;
    private boolean requiresPrescription; // Nécessite une ordonnance ?
}
