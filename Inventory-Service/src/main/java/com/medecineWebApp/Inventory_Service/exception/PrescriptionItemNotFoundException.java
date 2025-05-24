package com.medecineWebApp.Inventory_Service.exception;

public class PrescriptionItemNotFoundException extends RuntimeException {
    public PrescriptionItemNotFoundException(String message) {
        super(message);
    }
}
