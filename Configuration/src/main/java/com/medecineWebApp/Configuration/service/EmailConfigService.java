package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.SmtpConfigDTO;
import com.medecineWebApp.Configuration.models.setting.SmtpConfig;

import java.util.Optional;

public interface EmailConfigService {
    SmtpConfig createSmtpConfig(SmtpConfigDTO smtpConfigDTO);
   SmtpConfig updateSmtpConfig(Long id, SmtpConfigDTO smtpConfigDTO);
}
