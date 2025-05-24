package com.medecineWebApp.patients.utils;

import com.medecineWebApp.patients.enums.EventType;
import com.medecineWebApp.patients.events.PatientEvent;
import com.medecineWebApp.patients.models.Patient;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Aspect
@Component
public class PatientEventAspect {
    private static final String TOPIC_NAME = "patient-events";
    private final KafkaTemplate<String, PatientEvent> kafkaTemplate;

    public PatientEventAspect(KafkaTemplate<String, PatientEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @AfterReturning(value = "@annotation(publishPatientEvent)",returning = "result")
    public void publishPatientEvent(JoinPoint joinPoint,
                                    PublishPatientEvent publishPatientEvent,
                                    Object result
    ) {
        Object[] args = joinPoint.getArgs();
        PatientEvent patientEvent = new PatientEvent();
        if(publishPatientEvent.eventType() == EventType.CREATED && result instanceof Patient) {
            Patient patient = (Patient) result;
            patientEvent.setEventType(EventType.CREATED);
            patientEvent.setFirstname(patient.getFirstName());
            patientEvent.setRoles(Set.of(patient.getRoleId()));
            patientEvent.setLastname(patient.getLastName());
            patientEvent.setEmail(patient.getEmail());
            patientEvent.setPassword(patient.getPassword());

        } else if (publishPatientEvent.eventType() == EventType.UPDATED && args.length > 0 && args[0] instanceof Long) {
            Long userId = (Long) args[0];
            patientEvent.setUserId(userId);
            patientEvent.setEventType(EventType.UPDATED);

        } else if (publishPatientEvent.eventType() == EventType.DELETED && args.length > 0 && args[0] instanceof Long) {
            Long userId = (Long) args[0];
            patientEvent.setUserId(userId);
            patientEvent.setEventType(EventType.DELETED);

        }
        kafkaTemplate.send(TOPIC_NAME, patientEvent);
    }
}
