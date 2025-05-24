package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.enums.EventCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO extends AuditableDTO {
    private Long id;
    private String title;
    private LocalDateTime eventDate;
    private EventCategory category;
}
