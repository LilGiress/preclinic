package com.medecineWebApp.Configuration.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplateName,
            String confirmationUrl,
            String activationCode,
            String subject

            ) throws MessagingException {
        String templateName;
        if (emailTemplateName == null) {
            templateName="Email-confirmation";

        }else {
            templateName = emailTemplateName.getName();
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper Helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED, UTF_8.name());

        Map<String, Object> properties = new HashMap<>();
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activation_Code", activationCode);
        properties.put("username", username);

        Context context = new Context();
        context.setVariables(properties);

        Helper.setFrom("Test@demanou.com");
        Helper.setTo(to);
        Helper.setText(templateName, true);
        Helper.setSubject(subject);

        String template = templateEngine.process(templateName, context);


        Helper.setText(template, true);

        mailSender.send(mimeMessage);

    }
}
