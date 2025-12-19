package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.models.setting.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDTO extends AuditableDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private List<Event> events;
}
