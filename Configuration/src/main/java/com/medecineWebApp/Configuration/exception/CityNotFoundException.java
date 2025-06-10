package com.medecineWebApp.Configuration.exception;

public class CityNotFoundException extends RuntimeException{
    public  CityNotFoundException(String message){
        super(message);
    }
}
