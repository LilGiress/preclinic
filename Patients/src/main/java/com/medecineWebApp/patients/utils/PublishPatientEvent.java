package com.medecineWebApp.patients.utils;

import com.medecineWebApp.patients.enums.EventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishPatientEvent {
    EventType eventType();
}
