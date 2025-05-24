package com.medecineWebApp.Asset.Management.models;

import com.medecineWebApp.Asset.Management.enums.MaintenanceType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "assets_maintenance_log")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class AssetMaintenanceLog extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Assets asset;

    @ManyToOne
    @JoinColumn(name = "maintenance_id", nullable = false)
    private Maintenance maintenance;

    private LocalDate maintenanceDate;

    private String performedBy; // Technicien responsable
}
