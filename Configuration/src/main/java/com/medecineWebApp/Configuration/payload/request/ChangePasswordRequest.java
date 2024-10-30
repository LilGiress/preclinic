package com.medecineWebApp.Configuration.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {
    private Long userId;
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
