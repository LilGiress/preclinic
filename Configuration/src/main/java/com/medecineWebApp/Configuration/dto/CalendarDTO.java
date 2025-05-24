package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.models.setting.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDTO extends AuditableDTO {
    private Long id;

    private String name; // Example: "Doctor's Calendar"

    private List<EventDTO> events;

    // Add a user association if required (e.g., a Doctor or Patient)
    private Long userId;
}
