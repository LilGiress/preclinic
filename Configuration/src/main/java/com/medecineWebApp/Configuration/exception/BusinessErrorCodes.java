package com.medecineWebApp.Configuration.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, NOT_IMPLEMENTED,"No code"),

    ACCOUNT_LOCKED(302,FORBIDDEN,"User Account is locked"),
    ACCOUNT_DISABLED(303,FORBIDDEN,"User Account is disabled"),
    BAD_CREDENTIALS(304,FORBIDDEN,"login or password is incorrect"),
    ACCOUNT_NOT_FOUND(103, NOT_FOUND, "User account not found"),
    USER_ALREADY_AUTHENTICATED(104, BAD_REQUEST, "User is already authenticated"),


    // Inscription / Activation de compte
    EMAIL_ALREADY_EXISTS(200, CONFLICT, "Email is already in use"),
    USERNAME_ALREADY_EXISTS(201, CONFLICT, "Username is already taken"),
    PHONE_ALREADY_EXISTS(202, CONFLICT, "Phone number is already in use"),
    ACCOUNT_NOT_ACTIVATED(203, FORBIDDEN, "Account is not activated"),
    INVALID_REGISTRATION_DATA(204, BAD_REQUEST, "Invalid registration data"),

    // Mot de passe
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Current password is incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "The new password does not match"),
    PASSWORD_TOO_WEAK(302, BAD_REQUEST, "Password does not meet the security requirements"),
    PASSWORD_RESET_TOKEN_EXPIRED(303, UNAUTHORIZED, "Password reset token has expired"),


    // Token
    TOKEN_INVALID(400, UNAUTHORIZED, "Token is invalid"),
    TOKEN_EXPIRED(401, UNAUTHORIZED, "Activation token has expired . A new token has been sent to the same email address."),
    TOKEN_NOT_FOUND(402, NOT_FOUND, "Token not found"),
    TOKEN_ALREADY_USED(403, UNAUTHORIZED, "Token has already been used"),
    ACTIVATION_TOKEN_EXPIRED(404, UNAUTHORIZED, "Activation token has expired"),
    REFRESH_TOKEN_EXPIRED(405, UNAUTHORIZED, "Refresh token has expired"),

    // Fichiers / Uploads
    FILE_TOO_LARGE(500, PAYLOAD_TOO_LARGE, "Uploaded file is too large"),
    UNSUPPORTED_FILE_TYPE(501, UNSUPPORTED_MEDIA_TYPE, "Unsupported file type"),
    FILE_UPLOAD_FAILED(502, INTERNAL_SERVER_ERROR, "File upload failed"),

    // Données et validations
    INVALID_INPUT(600, BAD_REQUEST, "Invalid input data"),
    MISSING_REQUIRED_FIELDS(601, BAD_REQUEST, "Missing required fields"),
    VALIDATION_FAILED(602, BAD_REQUEST, "Validation failed"),
    RESOURCE_ALREADY_EXISTS(603, CONFLICT, "Resource already exists"),
    RESOURCE_NOT_FOUND(604, NOT_FOUND, "Requested resource not found"),

    // Sécurité
    ACCESS_DENIED(700, FORBIDDEN, "Access is denied"),
    UNAUTHORIZED_ACCESS(701, UNAUTHORIZED, "Unauthorized access"),
    SESSION_EXPIRED(702, UNAUTHORIZED, "Session has expired"),

    // Erreurs techniques
    INTERNAL_ERROR(800, INTERNAL_SERVER_ERROR, "An internal server error occurred"),
    SERVICE_UNAVAILABLE(801, HttpStatus.SERVICE_UNAVAILABLE, "Service is temporarily unavailable"),
    EXTERNAL_API_ERROR(802, BAD_GATEWAY, "Error from external service or API");
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
