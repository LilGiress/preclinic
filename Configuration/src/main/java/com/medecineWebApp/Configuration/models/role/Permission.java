package com.medecineWebApp.Configuration.models.role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medecineWebApp.Configuration.models.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Permission extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean canRead;   // Lire les informations
    private boolean canWrite;  // Modifier les informations
    private boolean canCreate; // Ajouter de nouvelles entrées
    private boolean canDelete; // Supprimer une entrée
    private boolean canImport; // Importer des données
    private boolean canExport; // Exporter des données
    private boolean canApprove; // Approuver des documents ou dossiers
    private boolean canValidate; // Valider un dossier médical
    private boolean canAssign;  // Assigner un médecin à un patient
    private boolean canGenerateReport; // Générer des rapports
    private boolean canActivate;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference
    private Roles role;



    public Permission( boolean canRead, boolean canWrite, boolean canCreate, boolean canDelete,
                      boolean canImport, boolean canExport, boolean canApprove, boolean canValidate,
                      boolean canAssign, boolean canGenerateReport, boolean canActivate) {

        this.canRead = canRead;
        this.canWrite = canWrite;
        this.canCreate = canCreate;
        this.canDelete = canDelete;
        this.canImport = canImport;
        this.canExport = canExport;
        this.canApprove = canApprove;
        this.canValidate = canValidate;
        this.canAssign = canAssign;
        this.canGenerateReport = canGenerateReport;
        this.canActivate = canActivate;
    }

    public Permission(boolean b, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8, boolean b9, boolean b10, boolean b11) {

        this.canRead = b1;
        this.canWrite = b2;
        this.canCreate = b3;
        this.canDelete = b4;
        this.canImport = b5;
        this.canExport = b6;
        this.canApprove = b7;
        this.canValidate = b8;
        this.canAssign = b9;
        this.canGenerateReport = b10;
        this.canActivate = b11;
    }
}
