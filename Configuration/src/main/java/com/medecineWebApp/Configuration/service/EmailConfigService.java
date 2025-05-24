package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.SmtpConfigDTO;
import com.medecineWebApp.Configuration.models.setting.SmtpConfig;

import java.util.Optional;

public interface EmailConfigService {
    SmtpConfigDTO createSmtpConfig(SmtpConfig smtpConfig);
    SmtpConfigDTO updateSmtpConfig(Long id, SmtpConfig smtpConfig);
}
