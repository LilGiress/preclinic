package com.medecineWebApp.Employees.services;



import com.medecineWebApp.Employees.dto.DoctorScheduleDTO;
import com.medecineWebApp.Employees.enums.ScheduleStatus;
import com.medecineWebApp.Employees.models.doctors.DoctorSchedule;
import org.springframework.data.domain.Page;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public interface DoctorScheduleService {
    Page<DoctorScheduleDTO> getAllDoctorSchedule(int page, int size);
    Optional<DoctorScheduleDTO> getDoctorScheduleById(Long id);
    DoctorScheduleDTO saveDoctorSchedule(DoctorSchedule doctorSchedule);
    void deleteDoctorScheduleById(Long id);
    DoctorScheduleDTO updateDoctorSchedule(Long id,DoctorSchedule doctorSchedule);
    Page<DoctorScheduleDTO> findSchedulesByDoctorAndDate(Long doctorId, LocalDate date,int page, int size);
    DoctorScheduleDTO setAvailableDays(Long scheduleId, EnumSet<DayOfWeek> availableDays);
    EnumSet<DayOfWeek> getAvailableDays(Long scheduleId);
    DoctorScheduleDTO updateScheduleStatus(Long id, ScheduleStatus status);
}
