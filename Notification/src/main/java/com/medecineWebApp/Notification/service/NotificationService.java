package com.medecineWebApp.Notification.service;

import com.medecineWebApp.Notification.models.Notification;
import com.medecineWebApp.Notification.models.kafka.NotificationEvent;
import com.medecineWebApp.Notification.models.kafka.PasswordResetEvent;
import com.medecineWebApp.Notification.models.kafka.UserEvent;
import com.medecineWebApp.Notification.repositories.NotificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private  final EmailService emailService;
    private final WebSocketNotificationService webSocketNotificationService;

    public NotificationService(NotificationRepository notificationRepository, JavaMailSender mailSender, TemplateEngine templateEngine, EmailService emailService, WebSocketNotificationService webSocketNotificationService) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.emailService = emailService;
        this.webSocketNotificationService = webSocketNotificationService;
    }


    @KafkaListener(topics = "notification-topic", groupId = "notification-service-group")
    public void handleNotificationEvent(NotificationEvent event) {
        System.out.println("Message reçu du topic Kafka: " + event.getMessage());

        // Enregistrement en base
        Notification notification = new Notification();
        notification.setMessage(event.getMessage());
        notification.setType(event.getType());
        notification.setUserName(event.getUserName());
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);
        System.out.println("✅ Notification enregistrée pour "  + " (ID: " + event.getUserName() + ")");

        // Envoi de l'email avec template HTML
        try {
            sendNotification(event);
        } catch (MessagingException e) {
            System.err.println("❌ Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
        // Logique d'envoi des notifications

    }

    private void sendNotification(NotificationEvent event) throws MessagingException {
        // Logique pour envoyer un e-mail ou une notification (par exemple, appel à un service)
        System.out.println("Envoi de la notification : " + event.getMessage());
//        User recipient = getEmailByUserId(event.getUserId());
//
//        if (recipient == null) {
//            System.err.println("❌ Aucun email trouvé pour l'ID : " + event.getUserId());
//            return;
//        }

        // Préparation du contexte Thymeleaf
        Context context = new Context();
        context.setVariable("recipientName", event.getUserName()); // Remplace par un vrai nom si dispo
        context.setVariable("message", event.getMessage());
        context.setVariable("type", event.getType());

        // Génération du contenu HTML
        String htmlContent = templateEngine.process("notification-email", context);

        // Création de l'email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("Test@demanou.com");
        helper.setTo(event.getUserEmail());
        helper.setSubject("📩 Nouvelle Notification : " + event.getType());
        helper.setText(htmlContent, true); // true = HTML

        mailSender.send(message);
        System.out.println("📧 Email HTML envoyé à " + event.getUserEmail());
    }

//    private User getEmailByUserId(Long userId) {
//        // Implémente la logique pour récupérer l'email de l'utilisateur (via un UserService, par ex)
//        return userClient.getUserById(userId); // Placeholder
//    }


    @KafkaListener(topics = "user-created-topic", groupId = "notification-group")
    public void sendWelcomeEmail(UserEvent event) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(event.getEmail());
            helper.setSubject("🎉 Bienvenue sur Clinique Santé !");

            // Utilisation de Thymeleaf
            Context context = new Context();
            context.setVariable("username", event.getUsername());
            context.setVariable("password", event.getPassword());
            String htmlContent = templateEngine.process("welcome-email", context);

            helper.setText(htmlContent, true);

            mailSender.send(message);
            System.out.println("✅ Email envoyé à " + event.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("❌ Erreur lors de l'envoi de l'email");
        }
    }

    @KafkaListener(topics = "password-reset-topic", groupId = "notification-group")
    public void sendPasswordResetEmail(PasswordResetEvent event) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(event.getEmail());
            helper.setSubject("🔑 Réinitialisation de votre mot de passe");

            // Utilisation de Thymeleaf
            Context context = new Context();
            context.setVariable("verificationCode", event.getVerificationCode());
            String htmlContent = templateEngine.process("reset-password-email", context);

            helper.setText(htmlContent, true);

            mailSender.send(message);
            System.out.println("✅ Email de réinitialisation envoyé à " + event.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("❌ Erreur lors de l'envoi de l'email");
        }
    }

    public void processNotification(Notification notification) {
        try {
            // Initialement non envoyé
            notification.setSent(false);

            // Sauvegarde initiale
            Notification saved = notificationRepository.save(notification);

            // Tentative d’envoi d’email
            boolean success = emailService.sendNotificationEmail(saved);

            if (success) {
                saved.setSent(true);
                notificationRepository.save(saved);
            }

            // Envoi temps réel via WebSocket
            webSocketNotificationService.sendRealTimeNotification(saved);

        } catch (Exception e) {
            // En cas d’échec, on peut logguer ou envoyer vers un "dead letter topic"
            System.err.println("Erreur de traitement notification : " + e.getMessage());
        }
    }
}
