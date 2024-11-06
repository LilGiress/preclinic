package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.AttendanceDTO;
import com.medecineWebApp.Employees.models.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);
    AttendanceDTO attendanceToAttendanceDTO(Attendance attendance);
    Attendance attendanceDTOToAttendance(AttendanceDTO attendanceDTO);
}
