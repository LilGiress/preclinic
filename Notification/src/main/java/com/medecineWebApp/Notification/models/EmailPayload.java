package com.medecineWebApp.Notification.models;


import com.medecineWebApp.Notification.notification.EmailTemplateName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailPayload {
    private String to;
    private String username;
    private String confirmationUrl;
    private String activationCode;
    private String subject;
    private EmailTemplateName emailTemplateName;
}
