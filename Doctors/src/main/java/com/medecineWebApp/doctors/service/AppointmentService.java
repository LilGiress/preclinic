package com.medecineWebApp.doctors.service;

import com.medecineWebApp.doctors.emuns.AppointmentStatus;
import com.medecineWebApp.doctors.models.Appointment;
import org.springframework.web.bind.annotation.PathVariable;

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
