package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.HolidayDTO;
import com.medecineWebApp.Configuration.mapper.HolidayMapper;
import com.medecineWebApp.Configuration.models.Holiday;
import com.medecineWebApp.Configuration.repository.HolidayRepository;
import com.medecineWebApp.Configuration.service.HolidayService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayServiceImpl implements HolidayService {
    private final HolidayRepository holidayRepository;
    private final HolidayMapper holidayMapper;

    public HolidayServiceImpl(HolidayRepository holidayRepository, HolidayMapper holidayMapper) {
        this.holidayRepository = holidayRepository;
        this.holidayMapper = holidayMapper;
    }

    @Override
    public void deleteHoliday(Long id) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holiday not found with id " + id));
        holidayRepository.delete(holiday);
    }

    @Override
    public boolean isHoliday(LocalDate date) {
        List<Holiday> holidays = holidayRepository.findByDate(date);
        return !holidays.isEmpty();
    }

    @Override
    public HolidayDTO updateHoliday(Long id, Holiday holidayDetails) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holiday not found with id " + id));

        holiday.setName(holidayDetails.getName());
        holiday.setDate(holidayDetails.getDate());

        return holidayMapper.HolidayToHolidayDTO(holidayRepository.save(holiday));
    }

    @Override
    public List<HolidayDTO> getHolidaysByDate(LocalDate date) {
        return holidayRepository.findByDate(date).stream()
                .map(holidayMapper::HolidayToHolidayDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HolidayDTO addHoliday(Holiday holiday) {
        return holidayMapper.HolidayToHolidayDTO(holidayRepository.save(holiday));
    }

    @Override
    public List<HolidayDTO> getAllHolidays() {
        return holidayRepository.findAll().stream()
                .map(holidayMapper::HolidayToHolidayDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HolidayDTO getHolidayById(Long id) {
        Holiday holiday= holidayRepository.findById(id).orElseThrow(() -> new RuntimeException("Holiday not found with id " + id));
        return holidayMapper.HolidayToHolidayDTO(holiday);
    }
}
