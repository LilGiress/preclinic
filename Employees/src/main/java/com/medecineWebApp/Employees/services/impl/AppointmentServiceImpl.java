package com.medecineWebApp.Employees.services.impl;


import com.medecineWebApp.Employees.feignClient.CountryClient;
import com.medecineWebApp.Employees.dto.AppointmentDTO;
import com.medecineWebApp.Employees.enums.AppointmentStatus;
import com.medecineWebApp.Employees.feignClient.PatientClient;
import com.medecineWebApp.Employees.filter.AppointmentSpecifications;
import com.medecineWebApp.Employees.mapper.AppointmentMapper;
import com.medecineWebApp.Employees.models.Appointment;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import com.medecineWebApp.Employees.repository.AppointmentRepository;
import com.medecineWebApp.Employees.services.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;



    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, CountryClient employeeClient, AppointmentMapper appointmentMapper, PatientClient patientClient) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public Page<AppointmentDTO> getAllAppointments(Long patient, Doctor doctor, String date, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Appointment> specification= Specification.where(
                AppointmentSpecifications.byDoctorId(doctor)
                        .and(AppointmentSpecifications.byPatientId(patient))
                        .and(AppointmentSpecifications.hasDate(date))
        );
        // Récupération de la page de rendez-vous depuis le repository
        Page<Appointment> appointmentPage = appointmentRepository.findAll(specification, pageable);
        // Mapping de chaque entité Appointment vers AppointmentDTO
        return appointmentPage.map(appointmentMapper::appointmentToAppointmentDTO);
    }

    @Override
    @Transactional
    public AppointmentDTO createAppointment(Appointment appointment) {
        if (appointment.getAppointmentCode() == null) {
            appointment.setAppointmentCode(generateAppointmentCode());
        }
        return appointmentMapper.appointmentToAppointmentDTO(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentDTO updateAppointment(Long id, Appointment appointmentDetails) {
        if (appointmentDetails.getPatientId() != null) {
           return appointmentRepository.findById(id).map(
                    appointmentMapper::appointmentToAppointmentDTO
            ).orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
        }
        throw new RuntimeException("Appointment not found with id " + id);

    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Optional<AppointmentDTO> getAppointmentById(Long id) {

        return appointmentRepository.findById(id).map(appointmentMapper::appointmentToAppointmentDTO);
    }

    @Override
    public List<AppointmentDTO> findAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        List<Appointment> appointmentList= appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date);
        return appointmentList.stream()
                .map(appointmentMapper::appointmentToAppointmentDTO)
                .toList();
    }

//    @Override
//    public List<AppointmentDTO> findAppointmentsByPatientAndDate(Patient patient, LocalDate date) {
//        Patient patient1=employeeClient.getPatient(patient.getId());
//        if (patient1 != null) {
//            List<Appointment> appointment = appointmentRepository.findByPatientAndAppointmentDate(patient1, date);
//            return appointment.stream()
//                    .map(appointmentMapper::appointmentToAppointmentDTO)
//                    .collect(Collectors.toList());
//        }
//        throw new RuntimeException("Patient not found with id " + patient.getId());
//
//    }

    @Override
    public List<AppointmentDTO> findAppointmentsByDate(LocalDate date) {
        List<Appointment> appointmentList= appointmentRepository.findByAppointmentDate(date);
        return appointmentList.stream()
                .map(appointmentMapper::appointmentToAppointmentDTO)
                .toList();
    }

    @Override
    public AppointmentDTO updateAppointmentStatus(Long id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
        appointment.setStatus(status);
        return appointmentMapper.appointmentToAppointmentDTO(appointmentRepository.save(appointment));
    }

    @Override
    public Long countAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.countAppointmentsByDoctor(doctorId);
    }

    @Override
    public List<AppointmentDTO> getUpcomingAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findUpcomingAppointmentsByDoctor(doctorId).stream()
                .map(appointmentMapper::appointmentToAppointmentDTO)
                .toList();
    }

    @Override
    public List<AppointmentDTO> getTodayAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findTodayAppointmentsByDoctor(doctorId)
                .stream()
                .map(appointmentMapper::appointmentToAppointmentDTO)
                .toList();
    }

    private String generateAppointmentCode() {
        long count = appointmentRepository.count(); // Count total appointments
        return String.format("APT-%04d", count + 1); // Generates APT-0001, APT-0002, etc.
    }

}
