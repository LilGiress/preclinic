package com.medecineWebApp.Configuration.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {
    private String email;
    private String verificationCode;
    private String newPassword;
    private String confirmationPassword;
}
