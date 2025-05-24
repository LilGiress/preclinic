package com.medecineWebApp.Employees.services;



import com.medecineWebApp.Employees.dto.AppointmentDTO;
import com.medecineWebApp.Employees.enums.AppointmentStatus;
import com.medecineWebApp.Employees.models.Appointment;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import org.springframework.data.domain.Page;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Page<AppointmentDTO> getAllAppointments(Long patient, Doctor doctor, String date, int page, int size);
    AppointmentDTO createAppointment(Appointment appointment);
    AppointmentDTO updateAppointment(Long id, Appointment appointmentDetails);
    void deleteAppointment(Long id);
    Optional<AppointmentDTO> getAppointmentById(Long id);
    List<AppointmentDTO> findAppointmentsByDoctorAndDate(Long doctorId, LocalDate date);
   // List<AppointmentDTO> findAppointmentsByPatientAndDate(Patient patientId, LocalDate date);
    List<AppointmentDTO> findAppointmentsByDate(LocalDate date);
    AppointmentDTO updateAppointmentStatus(Long id, AppointmentStatus status);
    Long countAppointmentsByDoctor(Long doctorId);
    List<AppointmentDTO> getUpcomingAppointmentsByDoctor(Long doctorId);
    List<AppointmentDTO> getTodayAppointmentsByDoctor(Long doctorId);

}
