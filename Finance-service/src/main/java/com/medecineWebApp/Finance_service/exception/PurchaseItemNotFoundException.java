package com.medecineWebApp.Finance_service.exception;

public class PurchaseItemNotFoundException extends RuntimeException {
    public PurchaseItemNotFoundException(String message) {
        super(message);
    }
}
