package com.medecineWebApp.Configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SmtpConfigDTO {
    private Long id;
    private String smtpHost;
    private int smtpPort;
    private String smtpUser;
    private String smtpPassword;
}
