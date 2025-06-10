package com.medecineWebApp.Configuration.exception;

public class SmtpConfigNotFoundException extends RuntimeException{
    public SmtpConfigNotFoundException(String message){
        super(message);
    }
}
