package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.HolidayDTO;
import com.medecineWebApp.Configuration.models.Holiday;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {
    void deleteHoliday(Long id);
    boolean isHoliday(LocalDate date);
    HolidayDTO updateHoliday(Long id, Holiday holidayDetails);
    List<HolidayDTO> getHolidaysByDate(LocalDate date);
    HolidayDTO addHoliday(Holiday holiday);
    List<HolidayDTO> getAllHolidays();
    HolidayDTO getHolidayById(Long id);
}
