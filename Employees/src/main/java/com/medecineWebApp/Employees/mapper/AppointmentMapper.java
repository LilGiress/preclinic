package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.AppointmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);
    AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);
    Appointment appointmentDTOToAppointment(AppointmentDTO appointmentDTO);
}
