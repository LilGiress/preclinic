package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.EventDTO;
import com.medecineWebApp.Configuration.models.setting.Event;
import com.medecineWebApp.Configuration.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Create a new event
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    // Get all events
    @GetMapping("/event/{calendarId}")
    public ResponseEntity<List<EventDTO>> getAllEvents(@PathVariable Long calendarId) {

        return ResponseEntity.ok(eventService.getAllEvents(calendarId));
    }

    // Get a specific event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<EventDTO>> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    // Update an event
    @PutMapping("/event/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        return ResponseEntity.ok(eventService.updateEvent(id, updatedEvent));
    }

    // Delete an event
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    // Ajouter un événement avec un jour férié spécifique
    @PostMapping("/addWithHoliday/{holidayId}")
    public EventDTO addEventWithHoliday(@RequestBody Event event, @PathVariable Long holidayId) {
        return eventService.addEventWithHoliday(event, holidayId);
    }

    // Récupérer les événements par année et mois
    @GetMapping("/year/{year}/month/{month}")
    public List<EventDTO> getEventsByYearAndMonth(@PathVariable int year, @PathVariable int month) {
        return eventService.getEventsByYearAndMonth(year, month);
    }

    // Récupérer tous les événements associés à un jour férié
    @GetMapping("/holiday/{holidayId}")
    public List<EventDTO> getEventsByHoliday(@PathVariable Long holidayId) {
        return eventService.getEventsByHoliday(holidayId);
    }
}
