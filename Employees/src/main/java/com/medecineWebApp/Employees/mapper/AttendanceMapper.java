package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.AttendanceDTO;
import com.medecineWebApp.Employees.models.Attendance;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    AttendanceDTO attendanceToAttendanceDTO(Attendance attendance);
    @InheritInverseConfiguration
    Attendance attendanceDTOToAttendance(AttendanceDTO attendanceDTO);
}
