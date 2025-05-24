package com.medecineWebApp.Asset.Management.dto;

import com.medecineWebApp.Asset.Management.models.Assets;
import com.medecineWebApp.Asset.Management.models.Maintenance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssetMaintenanceLogDTO {
    private Long id;

    private Assets asset;

    private Maintenance maintenance;

    private LocalDate maintenanceDate;

    private String performedBy; // Technicien responsable
}
