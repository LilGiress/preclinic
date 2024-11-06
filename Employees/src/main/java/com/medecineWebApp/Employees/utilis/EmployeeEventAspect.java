package com.medecineWebApp.Employees.utilis;

import com.medecineWebApp.Employees.enums.EventType;
import com.medecineWebApp.Employees.events.EmployeeEvent;
import com.medecineWebApp.Employees.models.Departement;
import com.medecineWebApp.Employees.models.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class EmployeeEventAspect {
    private static final String TOPIC_NAME = "employee-events";
    private KafkaTemplate<String, EmployeeEvent> kafkaTemplate;
@AfterReturning(value = "@annotation(publishEmployeeEvent)",returning = "result")
    public void publishEmployeeEvent(
            JoinPoint joinPoint,
            PublishEmployeeEvent publishEmployeeEvent,
            Object result) {
    Object[] args = joinPoint.getArgs();
    EmployeeEvent employeeEvent = new EmployeeEvent();

    if (publishEmployeeEvent.eventType()==EventType.CREATED && result instanceof Employee) {
        Employee employee = (Employee) result;
        employeeEvent.setEventType(EventType.CREATED);
        employeeEvent.setPassword(employee.getPassword());
        employeeEvent.setUsername(employee.getUsername());
        employeeEvent.setEmail(employee.getEmail());
        employeeEvent.setDepartments((List<Departement>) employee.getDepartment());
        employeeEvent.setRoles(employee.getRoles());
        employeeEvent.setUserId(employee.getUserId());


        } else if (publishEmployeeEvent.eventType()==EventType.UPDATED && args.length > 0 && args[0] instanceof Long) {
                Long employeeId = (Long) args[0];
                employeeEvent.setId(employeeId);
                employeeEvent.setEventType(EventType.UPDATED);

     }else if (publishEmployeeEvent.eventType()==EventType.DELETED && args[0] instanceof Long) {
        Long employeeId = (Long) args[0];
        employeeEvent.setId(employeeId);
        employeeEvent.setEventType(EventType.DELETED);
        }
    kafkaTemplate.send(TOPIC_NAME, employeeEvent);
    }
}
