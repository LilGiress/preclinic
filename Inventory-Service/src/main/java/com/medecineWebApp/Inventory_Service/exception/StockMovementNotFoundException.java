package com.medecineWebApp.Inventory_Service.exception;

public class StockMovementNotFoundException extends RuntimeException {
    public StockMovementNotFoundException(String message) {
        super(message);
    }
}
