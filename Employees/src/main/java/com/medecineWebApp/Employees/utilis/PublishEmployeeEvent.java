package com.medecineWebApp.Employees.utilis;

import com.medecineWebApp.Employees.enums.EventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishEmployeeEvent {
    EventType eventType();
}
