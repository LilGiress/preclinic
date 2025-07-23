package com.medecineWebApp.Configuration.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {
    private Integer businessErrorCode;
    private String businessErrorDescription;
    private String error;
    private Set<String> validationErrors;
    private Map<String,String> errors;
    private LocalDateTime timestamp = LocalDateTime.now();


    public static ResponseEntity<ExceptionResponse> withOnlyMessage(String message, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(ExceptionResponse.builder()
                        .error(message)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

}
