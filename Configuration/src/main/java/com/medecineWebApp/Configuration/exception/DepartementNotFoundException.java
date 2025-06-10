package com.medecineWebApp.Configuration.exception;

public class DepartementNotFoundException extends RuntimeException{
    public  DepartementNotFoundException(String message){
        super(message);
    }
}
