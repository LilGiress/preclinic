package com.medecineWebApp.Inventory_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDTO extends AuditableDTO{
    private Long id;

    private Long patientId;  // Provient de Patient-Service
    private Long doctorId;   // Provient de Employee-Service

    private List<PrescriptionItemDTO> items;

    private LocalDateTime issuedAt;
}
