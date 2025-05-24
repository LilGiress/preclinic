package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.models.Holiday;
import com.medecineWebApp.Configuration.models.setting.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCalendarId(Long calendarId);
    // Trouver des événements entre deux dates
    List<Event> findByEventDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Trouver des événements associés à un jour férié
    List<Event> findByHoliday(Holiday holiday);
}
