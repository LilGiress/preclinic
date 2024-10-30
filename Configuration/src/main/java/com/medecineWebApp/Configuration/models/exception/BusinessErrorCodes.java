package com.medecineWebApp.Configuration.models.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, NOT_IMPLEMENTED,"No code"),
    INCORRECT_CURRENT_PASSWORD(300,BAD_REQUEST,"Cureent password is Incorrect "),
    NEW_PASSWORD_DOES_NOT_MATCH(301,BAD_REQUEST,"The new password does not match "),
    ACCOUNT_LOCKED(302,FORBIDDEN,"User Account is locked"),
    ACCOUNT_DISABLED(303,FORBIDDEN,"User Account is disabled"),
    BAD_CREDENTIALS(304,FORBIDDEN,"login or password is incorrect"),


    ;
    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code,HttpStatus httpStatus, String description ) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;

    }
}
