package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.HolidayDTO;
import com.medecineWebApp.Configuration.models.Holiday;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HolidayMapper {
    HolidayDTO HolidayToHolidayDTO(Holiday holiday);
    @InheritInverseConfiguration
    Holiday HolidayDTOToHoliday(HolidayDTO holidayDTO);
}
