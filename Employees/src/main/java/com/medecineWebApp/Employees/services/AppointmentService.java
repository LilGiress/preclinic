package com.medecineWebApp.Employees.services;



import com.medecineWebApp.Employees.enums.AppointmentStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointment(Long id, Appointment appointmentDetails);
    void deleteAppointment(Long id);
    Optional<Appointment> getAppointmentById(Long id);
    List<Appointment> findAppointmentsByDoctorAndDate(Long doctorId, LocalDate date);
    List<Appointment> findAppointmentsByPatientAndDate(Long patientId, LocalDate date);
    List<Appointment> findAppointmentsByDate(LocalDate date);
    Appointment updateAppointmentStatus(Long id, AppointmentStatus status);
}
