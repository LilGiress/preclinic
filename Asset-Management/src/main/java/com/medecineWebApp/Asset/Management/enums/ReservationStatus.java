package com.medecineWebApp.Asset.Management.enums;

public enum ReservationStatus {
    PENDING,      // En attente de confirmation
    CONFIRMED,    // Confirmée
    CANCELLED,    // Annulée
    REJECTED,     // Refusée
    EXPIRED,      // Expirée
    IN_PROGRESS,  // En cours
    COMPLETED,    // Terminée
    FAILED,       // Échec
    REFUNDED,     // Remboursée
    NO_SHOW,      // Le client ne s'est pas présenté
    WAITLIST;     // En liste d’attente

    public boolean isFinalStatus() {
        return this == COMPLETED || this == CANCELLED || this == REJECTED || this == REFUNDED || this == EXPIRED;
    }
}
