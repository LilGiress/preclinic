package com.medecineWebApp.patients.models;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;

public class AuditLogListener {
    @PostPersist
    public void logCreation(Object entity) {
        System.out.println("Création de l'entité : " + entity);
    }

    @PostUpdate
    public void logUpdate(Object entity) {
        System.out.println("Mise à jour de l'entité : " + entity);
    }
}
