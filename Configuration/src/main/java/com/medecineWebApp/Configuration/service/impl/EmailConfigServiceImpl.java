package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.SmtpConfigDTO;
import com.medecineWebApp.Configuration.exception.SmtpConfigNotFoundException;
import com.medecineWebApp.Configuration.mapper.SmtpConfigMapper;
import com.medecineWebApp.Configuration.models.setting.SmtpConfig;
import com.medecineWebApp.Configuration.repository.EmailConfigRepository;
import com.medecineWebApp.Configuration.service.EmailConfigService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EmailConfigServiceImpl implements EmailConfigService {
    private final EmailConfigRepository emailConfigRepository;
    private final SmtpConfigMapper smtpConfigMapper;

    public EmailConfigServiceImpl(EmailConfigRepository emailConfigRepository, SmtpConfigMapper smtpConfigMapper) {
        this.emailConfigRepository = emailConfigRepository;
        this.smtpConfigMapper = smtpConfigMapper;
    }

    @Override
    public SmtpConfigDTO createSmtpConfig(SmtpConfig smtpConfig) {
        return smtpConfigMapper.smtpConfigToSmtpConfigDTO(emailConfigRepository.save(smtpConfig))   ;
    }

    @Override
    public SmtpConfigDTO updateSmtpConfig(Long id, SmtpConfig smtpConfig) {
        Optional<SmtpConfig> smtpConfigOptional = emailConfigRepository.findById(id);
        if (smtpConfigOptional.isPresent()) {
            SmtpConfig updatedSmtpConfig = smtpConfigOptional.get();
            updatedSmtpConfig.setSmtpPassword(smtpConfig.getSmtpPassword());
            updatedSmtpConfig.setSmtpHost(smtpConfig.getSmtpHost());
            updatedSmtpConfig.setSmtpPort(smtpConfig.getSmtpPort());
            updatedSmtpConfig.setSmtpUser(smtpConfig.getSmtpUser());
            return smtpConfigMapper.smtpConfigToSmtpConfigDTO( emailConfigRepository.save(updatedSmtpConfig));
        }
        throw new SmtpConfigNotFoundException("Smtp config not found");
    }
}
