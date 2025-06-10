package com.medecineWebApp.Configuration.exception;

public class CalendarNotFoundException extends RuntimeException{
    public  CalendarNotFoundException(String message){
        super(message);
    }
}
