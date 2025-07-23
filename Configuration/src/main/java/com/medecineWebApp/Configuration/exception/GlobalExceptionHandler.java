package com.medecineWebApp.Configuration.exception;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import static com.medecineWebApp.Configuration.exception.BusinessErrorCodes.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handlerException(LockedException exp){
            return ResponseEntity
                    .status(UNAUTHORIZED)
                    .body(
                            ExceptionResponse.builder()
                                    .businessErrorCode(ACCOUNT_LOCKED.getCode())
                                    .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                                    .error(exp.getMessage())
                                    .build()
                    );
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handlerException(DisabledException exp){
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ACCOUNT_DISABLED.getCode())
                                .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handlerException(BadCredentialsException exp){
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BAD_CREDENTIALS.getCode())
                                .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                                .error(BAD_CREDENTIALS.getDescription())
                                .build()
                );
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handlerException(MessagingException exp){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handlerException(MethodArgumentNotValidException exp){
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handlerException(Exception exp){
        exp.printStackTrace();
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("Internal error, please contact the administrator")
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription("Invalid argument")
                        .error(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(CustomAppException.class)
    public ResponseEntity<ExceptionResponse> handlerException(CustomAppException exp){
        BusinessErrorCodes code = exp.getErrorCode();
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(code.getCode())
                                .businessErrorDescription(code.getDescription())
                                .error(code.getDescription())
                                .build()
                );
    }

    private ResponseEntity<ExceptionResponse> buildErrorResponse(BusinessErrorCodes code, String message) {
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(ExceptionResponse.builder()
                        .businessErrorCode(code.getCode())
                        .businessErrorDescription(code.getDescription())
                        .error(message)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

//    @ExceptionHandler(LockedException.class)
//    public ResponseEntity<ExceptionResponse> handleLockedException(LockedException exp) {
//        return buildErrorResponse(ACCOUNT_LOCKED, exp.getMessage());
//    }

//    @ExceptionHandler(DisabledException.class)
//    public ResponseEntity<ExceptionResponse> handleDisabledException(DisabledException exp) {
//        return buildErrorResponse(ACCOUNT_DISABLED, exp.getMessage());
//    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ExceptionResponse> handleBadCredentials(BadCredentialsException exp) {
//        return buildErrorResponse(BAD_CREDENTIALS, BAD_CREDENTIALS.getDescription());
//    }

//    @ExceptionHandler(CustomAppException.class)
//    public ResponseEntity<ExceptionResponse> handleCustomAppException(CustomAppException exp) {
//        return buildErrorResponse(exp.getErrorCode(), exp.getMessage());
//    }

//    @ExceptionHandler(MessagingException.class)
//    public ResponseEntity<ExceptionResponse> handleMessagingException(MessagingException exp) {
//        return ExceptionResponse.withOnlyMessage(exp.getMessage(), INTERNAL_SERVER_ERROR);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ExceptionResponse> handleValidation(MethodArgumentNotValidException exp) {
//        Set<String> errors = new HashSet<>();
//        exp.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
//
//        return ResponseEntity
//                .status(BAD_REQUEST)
//                .body(ExceptionResponse.builder()
//                        .timestamp(LocalDateTime.now())
//                        .validationErrors(errors)
//                        .error("Validation failed")
//                        .build());
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ExceptionResponse> handleIllegalArgument(IllegalArgumentException ex) {
//        return buildErrorResponse(NO_CODE, ex.getMessage());
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<ExceptionResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
//        return buildErrorResponse(NO_CODE, "Type mismatch: " + ex.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> handleGenericException(Exception ex) {
//        ex.printStackTrace();
//        return buildErrorResponse(NO_CODE, "Internal error, please contact the administrator");
//    }
}
