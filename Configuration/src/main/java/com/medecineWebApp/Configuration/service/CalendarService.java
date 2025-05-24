package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.CalendarDTO;

public interface CalendarService {
    CalendarDTO getCalendarByUserId(Long userId);

}
