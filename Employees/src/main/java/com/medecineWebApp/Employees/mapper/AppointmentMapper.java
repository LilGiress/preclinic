package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.AppointmentDTO;
import com.medecineWebApp.Employees.models.Appointment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);
    @InheritInverseConfiguration
    Appointment appointmentDTOToAppointment(AppointmentDTO appointmentDTO);
}
