package com.medecineWebApp.Employees.services;



import com.medecineWebApp.Employees.enums.ScheduleStatus;
import com.medecineWebApp.Employees.models.doctors.DoctorSchedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public interface DoctorScheduleService {
    List<DoctorSchedule> getAllDoctorSchedule();
    Optional<DoctorSchedule> getDoctorScheduleById(Long id);
    DoctorSchedule saveDoctorSchedule(DoctorSchedule doctorSchedule);
    void deleteDoctorScheduleById(Long id);
    DoctorSchedule updateDoctorSchedule(Long id,DoctorSchedule doctorSchedule);
    List<DoctorSchedule> findSchedulesByDoctorAndDate(Long doctorId, LocalDate date);
    DoctorSchedule setAvailableDays(Long scheduleId, EnumSet<DayOfWeek> availableDays);
    EnumSet<DayOfWeek> getAvailableDays(Long scheduleId);
    DoctorSchedule updateScheduleStatus(Long id, ScheduleStatus status);
}
