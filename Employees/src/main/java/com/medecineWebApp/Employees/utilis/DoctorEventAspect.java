package com.medecineWebApp.Employees.utilis;

import com.medecineWebApp.Employees.enums.EventType;
import com.medecineWebApp.Employees.events.DoctorEvent;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Aspect
@Component
public class DoctorEventAspect {
    private static final String TOPIC_NAME = "doctor-events";
    private KafkaTemplate<String, DoctorEvent> kafkaTemplate;
@AfterReturning(value = "@annotation(publishDoctorEvent)",returning = "result")
    public  void publishDoctorEvent(JoinPoint joinPoint,
                                    PublishDoctorEvent publishDoctorEvent,
                                    Object result) {
        Object[] args = joinPoint.getArgs();
        DoctorEvent doctorEvent = new DoctorEvent();

        if (publishDoctorEvent.eventType() == EventType.CREATED && result instanceof Doctor) {
            Doctor doctor = (Doctor) result;
            doctorEvent.setEventType(EventType.CREATED);
            doctorEvent.setPassword(doctor.getPassword());
            doctorEvent.setEmail(doctor.getEmail());
            doctorEvent.setFirstname(doctor.getFirstname());
            doctorEvent.setLastname(doctor.getLastname());
           doctorEvent.setUserId(doctor.getUserId());
           doctorEvent.setDepartments(List.of(doctor.getDepartmentId()));
           doctorEvent.setRoles(Set.of(doctor.getRoleId()));




        } else if (publishDoctorEvent.eventType() == EventType.UPDATED && args.length > 0 && args[0] instanceof Long) {
            Long userId = (Long) args[0];
            doctorEvent.setUserId(userId);
            doctorEvent.setEventType(EventType.UPDATED);

        } else if (publishDoctorEvent.eventType() == EventType.DELETED && args[0] instanceof Long) {
            Long userId = (Long) args[0];
            doctorEvent.setUserId(userId);
            doctorEvent.setEventType(EventType.DELETED);

        }
        kafkaTemplate.send(TOPIC_NAME, doctorEvent);

    }
}
