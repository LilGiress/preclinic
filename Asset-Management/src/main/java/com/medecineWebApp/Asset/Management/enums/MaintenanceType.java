package com.medecineWebApp.Asset.Management.enums;

import lombok.Getter;

@Getter
public enum MaintenanceType {
    REPAIR("Réparation"),
    PREVENTIVE_MAINTENANCE("Entretien préventif"),
    CORRECTIVE_MAINTENANCE("Maintenance corrective"),
    INSPECTION("Inspection"),
    SOFTWARE_UPDATE("Mise à jour logicielle"),
    HARDWARE_REPLACEMENT("Remplacement de matériel"),
    CLEANING("Nettoyage"),
    CALIBRATION("Calibration"),
    SAFETY_CHECK("Vérification de sécurité");

    private final String label;

    MaintenanceType(String label) {
        this.label = label;
    }

}
