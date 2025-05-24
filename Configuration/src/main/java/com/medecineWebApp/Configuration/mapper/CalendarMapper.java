package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.CalendarDTO;
import com.medecineWebApp.Configuration.models.setting.Calendar;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CalendarMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    CalendarDTO calendarToCalendarDTO(Calendar calendar);
    @InheritInverseConfiguration
    Calendar calendarDTOToCalendar(CalendarDTO calendarDTO);
}
