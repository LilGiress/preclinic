package com.medecineWebApp.Configuration.exception;

public class RolesNotFoundException extends RuntimeException{
    public RolesNotFoundException(String message){
        super(message);
    }
}
