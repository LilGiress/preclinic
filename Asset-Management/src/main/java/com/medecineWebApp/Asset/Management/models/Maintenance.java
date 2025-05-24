package com.medecineWebApp.Asset.Management.models;

import com.medecineWebApp.Asset.Management.enums.MaintenanceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "maintenance_log")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Maintenance extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Assets asset;

    private LocalDateTime maintenanceDate;
    private String description;
    private String technician;
    @Enumerated(EnumType.STRING)
    private MaintenanceType maintenanceType; // "Réparation", "Entretien", etc.
    private String notes;
    @OneToMany(mappedBy = "maintenance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssetMaintenanceLog> assetMaintenanceLogs = new ArrayList<>();
}
