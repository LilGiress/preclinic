package com.medecineWebApp.Configuration.exception;

public class CompanySettingNotFoundException extends RuntimeException{
    public  CompanySettingNotFoundException(String message){
        super(message);
    }
}
