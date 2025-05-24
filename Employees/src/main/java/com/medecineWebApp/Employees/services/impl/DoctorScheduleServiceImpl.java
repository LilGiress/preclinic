package com.medecineWebApp.Employees.services.impl;


import com.medecineWebApp.Employees.dto.DoctorScheduleDTO;
import com.medecineWebApp.Employees.enums.ScheduleStatus;
import com.medecineWebApp.Employees.mapper.DoctorScheduleMapper;
import com.medecineWebApp.Employees.models.doctors.DoctorSchedule;
import com.medecineWebApp.Employees.repository.DoctorScheduleRepository;
import com.medecineWebApp.Employees.services.DoctorScheduleService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {
    private final DoctorScheduleRepository doctorScheduleRepository;
    private final DoctorScheduleMapper doctorScheduleMapper;

    public DoctorScheduleServiceImpl(DoctorScheduleRepository doctorScheduleRepository, DoctorScheduleMapper doctorScheduleMapper) {
        this.doctorScheduleRepository = doctorScheduleRepository;
        this.doctorScheduleMapper = doctorScheduleMapper;
    }

    @Override
    public Page<DoctorScheduleDTO> getAllDoctorSchedule(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DoctorSchedule> doctorSchedule = doctorScheduleRepository.findAll(pageable);
        return doctorSchedule.map(doctorScheduleMapper::DoctorScheduleToDoctorScheduleDTO);
    }

    @Override
    public Optional<DoctorScheduleDTO> getDoctorScheduleById(Long id) {
        Optional<DoctorSchedule> doctorSchedule = doctorScheduleRepository.findById(id);
        return doctorSchedule.map(doctorScheduleMapper::DoctorScheduleToDoctorScheduleDTO);
    }

    @Override
    public DoctorScheduleDTO saveDoctorSchedule(DoctorSchedule doctorSchedule) {

        return doctorScheduleMapper.DoctorScheduleToDoctorScheduleDTO(doctorScheduleRepository.save(doctorSchedule));
    }

    @Override
    public void deleteDoctorScheduleById(Long id) {
        doctorScheduleRepository.deleteById(id);
    }

    @Override
    public DoctorScheduleDTO updateDoctorSchedule(Long id, DoctorSchedule doctorSchedule) {
        if (id != null){
            return  doctorScheduleRepository.findById(id).map(
             doctorScheduleMapper::DoctorScheduleToDoctorScheduleDTO
        ).orElseThrow(
                    () -> new ResourceNotFoundException("doctorSchedule not found for this id: " + id)
            );

        }
        throw  new RuntimeException("Schedule not found with id " + id);

    }

    @Override
    public Page<DoctorScheduleDTO> findSchedulesByDoctorAndDate(Long doctorId, LocalDate date,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<DoctorSchedule> doctorSchedule= doctorScheduleRepository.findByDoctorIdAndDate(doctorId, date,pageable);
        return doctorSchedule.map(doctorScheduleMapper::DoctorScheduleToDoctorScheduleDTO);
    }

    @Override
    public DoctorScheduleDTO setAvailableDays(Long scheduleId, EnumSet<DayOfWeek> availableDays) {
        DoctorSchedule schedule = doctorScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + scheduleId));
        schedule.setAvailableDays(availableDays);

        return doctorScheduleMapper.DoctorScheduleToDoctorScheduleDTO(doctorScheduleRepository.save(schedule));
    }

    @Override
    public EnumSet<DayOfWeek> getAvailableDays(Long scheduleId) {
        return doctorScheduleRepository.findById(scheduleId)
                .map(DoctorSchedule::getAvailableDays)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + scheduleId));
    }

    @Override
    public DoctorScheduleDTO updateScheduleStatus(Long id, ScheduleStatus status) {
        DoctorSchedule schedule = doctorScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + id));
        schedule.setStatus(status);
        return doctorScheduleMapper.DoctorScheduleToDoctorScheduleDTO(doctorScheduleRepository.save(schedule));
    }


}
