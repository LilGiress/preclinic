package com.medecineWebApp.doctors.repository;

import com.medecineWebApp.doctors.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);
    List<Appointment> findByPatientIdAndAppointmentDate(Long patientId, LocalDate date);
    List<Appointment> findByAppointmentDate(LocalDate date);
}
