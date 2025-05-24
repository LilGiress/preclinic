package com.medecineWebApp.Employees.exception;

public class DoctorScheduleNotFoundException extends RuntimeException {
    public DoctorScheduleNotFoundException(String message) {
        super(message);
    }
}
