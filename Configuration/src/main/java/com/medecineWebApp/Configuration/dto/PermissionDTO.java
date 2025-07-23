package com.medecineWebApp.Configuration.dto;


import com.medecineWebApp.Configuration.models.role.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO extends AuditableDTO {
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
    private Roles role;

}
