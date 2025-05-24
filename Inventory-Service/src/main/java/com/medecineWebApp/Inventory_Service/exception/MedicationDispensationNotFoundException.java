package com.medecineWebApp.Inventory_Service.exception;

public class MedicationDispensationNotFoundException extends RuntimeException {
    public MedicationDispensationNotFoundException(String message) {
        super(message);
    }
}
