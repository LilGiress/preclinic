package com.medecineWebApp.doctors.service.impl;

import com.medecineWebApp.doctors.emuns.ScheduleStatus;
import com.medecineWebApp.doctors.models.DoctorSchedule;
import com.medecineWebApp.doctors.repository.DoctorScheduleRepository;
import com.medecineWebApp.doctors.service.DoctorScheduleService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {
    private final DoctorScheduleRepository doctorScheduleRepository;

    public DoctorScheduleServiceImpl(DoctorScheduleRepository doctorScheduleRepository) {
        this.doctorScheduleRepository = doctorScheduleRepository;
    }

    @Override
    public List<DoctorSchedule> getAllDoctorSchedule() {
        return doctorScheduleRepository.findAll();
    }

    @Override
    public Optional<DoctorSchedule> getDoctorScheduleById(Long id) {
        return doctorScheduleRepository.findById(id);
    }

    @Override
    public DoctorSchedule saveDoctorSchedule(DoctorSchedule doctorSchedule) {
        return doctorScheduleRepository.save(doctorSchedule);
    }

    @Override
    public void deleteDoctorScheduleById(Long id) {
        doctorScheduleRepository.deleteById(id);
    }

    @Override
    public DoctorSchedule updateDoctorSchedule(Long id, DoctorSchedule doctorSchedule) {
        return doctorScheduleRepository.findById(id)
                .map(schedule -> {
                    schedule.setDate(doctorSchedule.getDate());
                    schedule.setStartTime(doctorSchedule.getStartTime());
                    schedule.setEndTime(doctorSchedule.getEndTime());
                    schedule.setStatus(doctorSchedule.getStatus());
                    schedule.setAvailableDays(doctorSchedule.getAvailableDays());
                    schedule.setDoctorId(doctorSchedule.getDoctorId());
                    schedule.setMessage(doctorSchedule.getMessage());
                    return doctorScheduleRepository.save(schedule);
                })
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + id));
    }

    @Override
    public List<DoctorSchedule> findSchedulesByDoctorAndDate(Long doctorId, LocalDate date) {
        return doctorScheduleRepository.findByDoctorIdAndDate(doctorId, date);
    }

    @Override
    public DoctorSchedule setAvailableDays(Long scheduleId, EnumSet<DayOfWeek> availableDays) {
        DoctorSchedule schedule = doctorScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + scheduleId));
        schedule.setAvailableDays(availableDays);
        return doctorScheduleRepository.save(schedule);
    }

    @Override
    public EnumSet<DayOfWeek> getAvailableDays(Long scheduleId) {
        return doctorScheduleRepository.findById(scheduleId)
                .map(DoctorSchedule::getAvailableDays)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + scheduleId));
    }

    @Override
    public DoctorSchedule updateScheduleStatus(Long id, ScheduleStatus status) {
        DoctorSchedule schedule = doctorScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + id));
        schedule.setStatus(status);
        return doctorScheduleRepository.save(schedule);
    }


}
