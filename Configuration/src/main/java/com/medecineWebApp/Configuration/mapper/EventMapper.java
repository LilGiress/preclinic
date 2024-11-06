package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.EventDTO;
import com.medecineWebApp.Configuration.models.setting.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
    EventDTO eventToEventDTO(Event event);
    Event eventDTOToEvent(EventDTO eventDTO);
}
