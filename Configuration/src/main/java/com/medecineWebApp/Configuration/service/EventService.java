package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.EventDTO;
import com.medecineWebApp.Configuration.models.setting.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    EventDTO createEvent(Event event);
    EventDTO updateEvent(Long id, Event event);
    Optional<EventDTO> getEventById(Long id);
    void deleteEvent(Long id);
    List<EventDTO> getAllEvents(Long calendarId);
    EventDTO addEventWithHoliday(Event event, Long holidayId);
    List<EventDTO> getEventsByYearAndMonth(int year, int month);
    List<EventDTO> getEventsByHoliday(Long holidayId);
}
