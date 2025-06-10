package com.medecineWebApp.Configuration.exception;

public class ThemeSettingNotFoundException extends RuntimeException{
    public ThemeSettingNotFoundException(String message){
        super(message);
    }
}
