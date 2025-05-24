package com.medecineWebApp.Asset.Management.dto;

import com.medecineWebApp.Asset.Management.enums.AssetCategory;
import com.medecineWebApp.Asset.Management.enums.AssetStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssetsDTO extends AuditableDTO{
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private AssetCategory category;

    private LocalDate acquisitionDate;

    private Double value;

    private String description;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    private LocalDate purchaseDate;

    private String purchaseFrom;

    private String Model;

    private String serialNumber;

    private String supplier;

    private String conditionAsset;

    private Number Warranty;
    private Long assignedDoctorId;
    private LocalDate lastMaintenanceDate;
    private LocalDate nextMaintenanceDate;
}
