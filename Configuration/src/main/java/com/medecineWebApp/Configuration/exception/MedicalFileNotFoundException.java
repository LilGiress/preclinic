package com.medecineWebApp.Configuration.exception;

public class MedicalFileNotFoundException extends RuntimeException{
    public  MedicalFileNotFoundException(String message){
        super(message);
    }
}
