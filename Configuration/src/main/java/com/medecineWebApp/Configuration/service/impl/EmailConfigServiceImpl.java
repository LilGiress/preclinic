package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.models.setting.SmtpConfig;
import com.medecineWebApp.Configuration.repository.EmailConfigRepository;
import com.medecineWebApp.Configuration.service.EmailConfigService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EmailConfigServiceImpl implements EmailConfigService {
    private final EmailConfigRepository emailConfigRepository;

    public EmailConfigServiceImpl(EmailConfigRepository emailConfigRepository) {
        this.emailConfigRepository = emailConfigRepository;
    }

    @Override
    public SmtpConfig createSmtpConfig(SmtpConfig smtpConfig) {
        return emailConfigRepository.save(smtpConfig);
    }

    @Override
    public SmtpConfig updateSmtpConfig(Long id, SmtpConfig smtpConfig) {
        Optional<SmtpConfig> smtpConfigOptional = emailConfigRepository.findById(id);
        if (smtpConfigOptional.isPresent()) {
            SmtpConfig updatedSmtpConfig = smtpConfigOptional.get();
            updatedSmtpConfig.setSmtpPassword(smtpConfig.getSmtpPassword());
            updatedSmtpConfig.setSmtpHost(smtpConfig.getSmtpHost());
            updatedSmtpConfig.setSmtpPort(smtpConfig.getSmtpPort());
            updatedSmtpConfig.setSmtpUser(smtpConfig.getSmtpUser());
            return emailConfigRepository.save(updatedSmtpConfig);
        }
        throw new NotFoundException("Smtp config not found");
    }
}
