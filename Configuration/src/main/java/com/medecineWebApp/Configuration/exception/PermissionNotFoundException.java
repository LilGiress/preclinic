package com.medecineWebApp.Configuration.exception;

public class PermissionNotFoundException extends RuntimeException{
    public  PermissionNotFoundException(String message){
        super(message);
    }
}
