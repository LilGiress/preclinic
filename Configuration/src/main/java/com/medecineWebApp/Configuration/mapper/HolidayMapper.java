package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.HolidayDTO;
import com.medecineWebApp.Configuration.models.Holiday;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HolidayMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    HolidayDTO HolidayToHolidayDTO(Holiday holiday);
    @InheritInverseConfiguration
    Holiday HolidayDTOToHoliday(HolidayDTO holidayDTO);
}
