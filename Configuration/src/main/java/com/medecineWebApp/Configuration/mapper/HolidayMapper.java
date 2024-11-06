package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.HolidayDTO;
import com.medecineWebApp.Configuration.models.Holiday;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HolidayMapper {
    HolidayMapper INSTANCE = Mappers.getMapper(HolidayMapper.class);
    HolidayDTO HolidayToHolidayDTO(Holiday holiday);
    Holiday HolidayDTOToHoliday(HolidayDTO holidayDTO);
}
