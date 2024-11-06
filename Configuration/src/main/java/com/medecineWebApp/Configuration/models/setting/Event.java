package com.medecineWebApp.Configuration.models.setting;

import com.medecineWebApp.Configuration.enums.EventCategory;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "config_events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime eventDate;

    @Enumerated(EnumType.STRING)
    private EventCategory category;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }


    public Event(String title, LocalDateTime eventDate, EventCategory category) {
        this.title = title;
        this.eventDate = eventDate;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", category=" + category +
                '}';
    }
}
