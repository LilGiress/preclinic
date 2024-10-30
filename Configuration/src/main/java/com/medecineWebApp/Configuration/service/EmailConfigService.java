package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.models.setting.SmtpConfig;

import java.util.Optional;

public interface EmailConfigService {
    SmtpConfig createSmtpConfig(SmtpConfig smtpConfig);
   SmtpConfig updateSmtpConfig(Long id, SmtpConfig smtpConfig);
}
