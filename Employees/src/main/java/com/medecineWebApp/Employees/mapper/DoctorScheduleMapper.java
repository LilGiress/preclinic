package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.DoctorScheduleDTO;
import com.medecineWebApp.Employees.models.doctors.DoctorSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DoctorScheduleMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    DoctorScheduleDTO DoctorScheduleToDoctorScheduleDTO(DoctorSchedule doctorSchedule);
}
