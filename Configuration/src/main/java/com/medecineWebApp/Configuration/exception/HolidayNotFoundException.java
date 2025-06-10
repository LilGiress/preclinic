package com.medecineWebApp.Configuration.exception;

public class HolidayNotFoundException extends RuntimeException{
    public  HolidayNotFoundException(String message){
        super(message);
    }
}
