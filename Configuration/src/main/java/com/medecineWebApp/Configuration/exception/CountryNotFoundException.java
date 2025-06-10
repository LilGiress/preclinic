package com.medecineWebApp.Configuration.exception;

public class CountryNotFoundException extends RuntimeException{
    public  CountryNotFoundException(String message){
        super(message);
    }
}
