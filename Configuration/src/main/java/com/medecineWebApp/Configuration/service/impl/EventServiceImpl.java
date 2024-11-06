package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.EventDTO;
import com.medecineWebApp.Configuration.mapper.EventMapper;
import com.medecineWebApp.Configuration.models.setting.Event;
import com.medecineWebApp.Configuration.repository.EventRepository;
import com.medecineWebApp.Configuration.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public Event createEvent(EventDTO eventDTO) {
        return eventRepository.save(eventMapper.eventDTOToEvent(eventDTO));
    }

    @Override
    public Event updateEvent(Long id, EventDTO updatedEvent) {
       Optional<Event> optionalEvent = eventRepository.findById(id);
       if (optionalEvent.isPresent()) {
           Event event = optionalEvent.get();
           event.setEventDate(updatedEvent.getEventDate());
           event.setCategory(updatedEvent.getCategory());
           event.setTitle(updatedEvent.getTitle());

           return eventRepository.save(event);
       }
        throw new RuntimeException("No event found with id " + id);
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return Optional.ofNullable(eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found")));

    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
