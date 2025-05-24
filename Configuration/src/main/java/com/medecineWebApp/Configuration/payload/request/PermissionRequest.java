package com.medecineWebApp.Configuration.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequest {
    private String module;
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
}
