package com.medecineWebApp.Configuration.exception;

public class SalarySettingNotFoundException extends RuntimeException{
    public SalarySettingNotFoundException(String message){
        super(message);
    }
}
