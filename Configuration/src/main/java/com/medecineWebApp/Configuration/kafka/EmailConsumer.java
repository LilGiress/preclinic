package com.medecineWebApp.Configuration.kafka;


import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
  //  @Autowired
   // private EmailService emailService;

    /*@KafkaListener(topics = "email-notifications", groupId = "email-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(EmailPayload payload) {
        System.out.println("Message consommé depuis Kafka : " + payload);

        try {
            // Appel du service pour envoyer l'email
            emailService.sendEmail(
                    payload.getTo(),
                    payload.getUsername(),
                    payload.getEmailTemplateName(),
                    payload.getConfirmationUrl(),
                    payload.getActivationCode(),
                    payload.getSubject()
            );
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }*/
}
