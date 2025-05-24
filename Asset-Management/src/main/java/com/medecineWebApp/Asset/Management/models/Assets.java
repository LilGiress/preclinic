package com.medecineWebApp.Asset.Management.models;

import com.medecineWebApp.Asset.Management.enums.AssetCategory;
import com.medecineWebApp.Asset.Management.enums.AssetStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assets")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Assets extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int usageCount; // Nombre d’utilisations

    private int maintenanceThreshold; // Seuil d’entretien (jours ou nombre d’utilisations)
    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssetMaintenanceLog> maintenanceLogs = new ArrayList<>();
    private boolean isOperational; // L’équipement fonctionne-t-il ?
    private String location; // Ex: Bloc opératoire, Service des urgences
    private Long departmentId;
    private boolean maintenanceRequired; // Champ qui indique si l'asset nécessite une maintenance

}
