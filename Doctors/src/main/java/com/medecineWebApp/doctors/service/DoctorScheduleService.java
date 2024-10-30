package com.medecineWebApp.doctors.service;

import com.medecineWebApp.doctors.emuns.ScheduleStatus;
import com.medecineWebApp.doctors.models.DoctorSchedule;

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
