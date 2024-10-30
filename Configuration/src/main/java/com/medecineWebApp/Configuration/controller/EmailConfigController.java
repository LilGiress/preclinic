package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.models.setting.SmtpConfig;
import com.medecineWebApp.Configuration.repository.EmailConfigRepository;
import com.medecineWebApp.Configuration.service.EmailConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email-config")
public class EmailConfigController {

    private final EmailConfigService emailConfigService;

    public EmailConfigController(EmailConfigService emailConfigService) {
        this.emailConfigService = emailConfigService;
    }

    // Fetch current configuration (GET)
    @PostMapping("/save")
    public ResponseEntity<SmtpConfig>  saveEmailConfig(SmtpConfig smtpConfig) {
        return ResponseEntity.ok(emailConfigService.createSmtpConfig(smtpConfig)) ;
    }

    // Update  configuration (POST)
    @PutMapping ("/update")
    public ResponseEntity<SmtpConfig> updateEmailConfig(@RequestParam Long id,@RequestBody SmtpConfig emailConfig) {
        return ResponseEntity.ok(emailConfigService.updateSmtpConfig(id, emailConfig));
    }
}
