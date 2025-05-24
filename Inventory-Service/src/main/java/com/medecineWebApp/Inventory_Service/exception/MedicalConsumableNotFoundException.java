package com.medecineWebApp.Inventory_Service.exception;

public class MedicalConsumableNotFoundException extends RuntimeException {
    public MedicalConsumableNotFoundException(String message) {
        super(message);
    }
}
