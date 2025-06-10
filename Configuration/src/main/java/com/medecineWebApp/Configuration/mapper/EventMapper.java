package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.EventDTO;
import com.medecineWebApp.Configuration.models.setting.Event;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO eventToEventDTO(Event event);
    @InheritInverseConfiguration
    Event eventDTOToEvent(EventDTO eventDTO);
}
