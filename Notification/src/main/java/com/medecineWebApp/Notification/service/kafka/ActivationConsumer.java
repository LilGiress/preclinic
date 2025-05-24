package com.medecineWebApp.Notification.service.kafka;

import com.medecineWebApp.Notification.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class ActivationConsumer {
    private final EmailService emailService;

    public ActivationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }
}
