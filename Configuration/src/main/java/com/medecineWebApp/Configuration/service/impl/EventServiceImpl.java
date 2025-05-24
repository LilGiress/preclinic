package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.EventDTO;
import com.medecineWebApp.Configuration.mapper.EventMapper;
import com.medecineWebApp.Configuration.models.Holiday;
import com.medecineWebApp.Configuration.models.setting.Event;
import com.medecineWebApp.Configuration.repository.EventRepository;
import com.medecineWebApp.Configuration.repository.HolidayRepository;
import com.medecineWebApp.Configuration.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final HolidayRepository holidayRepository;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, HolidayRepository holidayRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.holidayRepository = holidayRepository;
    }

    @Override
    public EventDTO createEvent(Event event) {
        return eventMapper.eventToEventDTO(eventRepository.save(event)) ;
    }

    @Override
    public EventDTO updateEvent(Long id, Event updatedEvent) {
       Optional<Event> optionalEvent = eventRepository.findById(id);
       if (optionalEvent.isPresent()) {
           Event event = optionalEvent.get();
           event.setEventDate(updatedEvent.getEventDate());
           event.setCategory(updatedEvent.getCategory());
           event.setTitle(updatedEvent.getTitle());

           return eventMapper.eventToEventDTO(eventRepository.save(event));
       }
        throw new RuntimeException("No event found with id " + id);
    }

    @Override
    public Optional<EventDTO> getEventById(Long id) {
        return  eventRepository.findById(id).map(eventMapper::eventToEventDTO);


    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDTO> getAllEvents(Long calendarId) {
        return eventRepository.findByCalendarId(calendarId).stream().map(eventMapper::eventToEventDTO).toList();
    }

    @Override
    public EventDTO addEventWithHoliday(Event event, Long holidayId) {
        Holiday holiday = holidayRepository.findById(holidayId).orElse(null);
        if (holiday != null) {
            event.setHoliday(holiday);
            return eventMapper.eventToEventDTO(eventRepository.save(event));
        }
        return null;
    }

    @Override
    public List<EventDTO> getEventsByYearAndMonth(int year, int month) {
        // Créer la date de début du mois à partir de l'année et du mois
        LocalDate startDate = LocalDate.of(year, month, 1);

        // Créer la date de fin du mois en ajoutant un mois et en ajustant le jour
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        // Convertir les LocalDate en LocalDateTime avec l'heure 00:00:00 pour la date de début
        LocalDateTime startDateTime = startDate.atTime(0, 0);

        // Convertir LocalDateTime pour la fin de mois à 23:59:59
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        return eventRepository.findByEventDateBetween(startDateTime, endDateTime)
                .stream().map(eventMapper::eventToEventDTO)
                .toList();
    }

    @Override
    public List<EventDTO> getEventsByHoliday(Long holidayId) {
        Holiday holiday = holidayRepository.findById(holidayId).orElse(null);
        if (holiday != null) {
            return eventRepository.findByHoliday(holiday).stream()
                    .map(eventMapper::eventToEventDTO)
                    .toList();
        }
        return null;
    }
}
