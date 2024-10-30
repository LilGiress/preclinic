package com.medecineWebApp.doctors.service.impl;

import com.medecineWebApp.doctors.emuns.AppointmentStatus;
import com.medecineWebApp.doctors.models.Appointment;
import com.medecineWebApp.doctors.repository.AppointmentRepository;
import com.medecineWebApp.doctors.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    @Transactional
    public Appointment createAppointment(Appointment appointment) {
        if (appointment.getAppointmentCode() == null) {
            appointment.setAppointmentCode(generateAppointmentCode());
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointment.setDoctorId(appointmentDetails.getDoctorId());
                    appointment.setPatientId(appointmentDetails.getPatientId());
                    appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
                    appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
                    appointment.setStatus(appointmentDetails.getStatus());
                    appointment.setPatientPhoneNumber(appointmentDetails.getPatientPhoneNumber());
                    appointment.setMessage(appointmentDetails.getMessage());
                    appointment.setEmail(appointmentDetails.getEmail());
                    appointment.setDepartmentId(appointmentDetails.getDepartmentId());
                    appointment.setAppointmentCode(appointmentDetails.getAppointmentCode());
                    return appointmentRepository.save(appointment);
                })
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> findAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date);
    }

    @Override
    public List<Appointment> findAppointmentsByPatientAndDate(Long patientId, LocalDate date) {
        return appointmentRepository.findByPatientIdAndAppointmentDate(patientId, date);
    }

    @Override
    public List<Appointment> findAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    @Override
    public Appointment updateAppointmentStatus(Long id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    private String generateAppointmentCode() {
        long count = appointmentRepository.count(); // Count total appointments
        return String.format("APT-%04d", count + 1); // Generates APT-0001, APT-0002, etc.
    }

}
