package com.medecineWebApp.Configuration.exception;

public class LeaveNotFoundException extends RuntimeException{
    public  LeaveNotFoundException(String message){
        super(message);
    }
}
