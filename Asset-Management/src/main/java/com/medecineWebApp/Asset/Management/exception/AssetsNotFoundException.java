package com.medecineWebApp.Asset.Management.exception;

public class AssetsNotFoundException extends RuntimeException {
    public AssetsNotFoundException(String message) {
        super(message);
    }
}
