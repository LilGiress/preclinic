package com.medecineWebApp.Employees.repository;


import com.medecineWebApp.Employees.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {

    @Query(value = "SELECT COUNT(*) FROM Appointment WHERE doctor_id = :doctorId",
            nativeQuery = true)
    Long countAppointmentsByDoctor(@Param("doctorId") Long doctorId);

    @Query(value = "SELECT * FROM Appointment WHERE doctor_id = :doctorId AND appointment_date > NOW() ORDER BY appointment_date ASC",
            nativeQuery = true)
    List<Appointment> findUpcomingAppointmentsByDoctor(@Param("doctorId") Long doctorId);

    @Query(value = "SELECT * FROM Appointment WHERE doctor_id = :doctorId AND DATE(appointment_date) = CURRENT_DATE ORDER BY appointment_date ASC",
            nativeQuery = true)
    List<Appointment> findTodayAppointmentsByDoctor(@Param("doctorId") Long doctorId);

    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);
   // List<Appointment> findByPatientAndAppointmentDate(Patient patientId, LocalDate date);
    List<Appointment> findByAppointmentDate(LocalDate date);
   // List<Appointment> findByPatientAndAppointmentDate(Patient patient, LocalDate date);


}
