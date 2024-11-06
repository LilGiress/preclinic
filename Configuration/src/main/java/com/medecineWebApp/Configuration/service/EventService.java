package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.EventDTO;
import com.medecineWebApp.Configuration.models.setting.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event createEvent(EventDTO eventDTO);
    Event updateEvent(Long id, EventDTO eventDTO);
    Optional<Event> getEventById(Long id);
    void deleteEvent(Long id);
    List<Event> getAllEvents();
}
