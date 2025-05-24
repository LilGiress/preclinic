package com.medecineWebApp.Asset.Management.dto;

import com.medecineWebApp.Asset.Management.models.Assets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDTO {
    private Long id;

    private Assets asset;

    private LocalDateTime maintenanceDate;
    private String description;
    private String technician;
    private String status; // "Effectuée", "En attente", "Urgente"
    private String notes;
}
