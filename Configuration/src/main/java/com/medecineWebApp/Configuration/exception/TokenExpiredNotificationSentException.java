package com.medecineWebApp.Configuration.exception;

public class TokenExpiredNotificationSentException extends RuntimeException {
    public TokenExpiredNotificationSentException(String message) {
        super(message);
    }
}
