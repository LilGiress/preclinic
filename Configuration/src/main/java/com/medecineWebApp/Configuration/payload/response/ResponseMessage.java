package com.medecineWebApp.Configuration.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseMessage<T> {
    int status;
    String message;
    T data;
    String newToken;

    public ResponseMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseMessage(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage(int status, String message, T data, String newToken) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.newToken = newToken;
    }
}
