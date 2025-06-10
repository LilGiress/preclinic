package com.medecineWebApp.Configuration.exception;

public class LeaveTypeNotFoundException extends RuntimeException{
    public  LeaveTypeNotFoundException(String message){
        super(message);
    }
}
