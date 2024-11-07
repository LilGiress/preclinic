package com.medecineWebApp.patients.enums;

public enum TreatmentStatus {
    ONGOING,      // Le traitement est en cours
    COMPLETED,    // Le traitement est terminé
    CANCELLED,    // Le traitement a été annulé
    PENDING,      // Le traitement est en attente de confirmation ou de démarrage
    SUSPENDED     // Le traitement est suspendu temporairement
}
