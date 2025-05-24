package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.CalendarDTO;
import com.medecineWebApp.Configuration.mapper.CalendarMapper;
import com.medecineWebApp.Configuration.repository.CalendarRepository;
import com.medecineWebApp.Configuration.service.CalendarService;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;
    private final CalendarMapper calendarMapper;

    public CalendarServiceImpl(CalendarRepository calendarRepository, CalendarMapper calendarMapper) {
        this.calendarRepository = calendarRepository;
        this.calendarMapper = calendarMapper;
    }

    @Override
    public CalendarDTO getCalendarByUserId(Long userId) {
        return calendarMapper.calendarToCalendarDTO(calendarRepository.findByUserId(userId));
    }
}
