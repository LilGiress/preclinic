package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.LeaveTypeDTO;
import com.medecineWebApp.Configuration.models.LeaveType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveTypeMapper {
    LeaveTypeDTO LeaveTypeToLeaveTypeDTO(LeaveType leaveType);
    @InheritInverseConfiguration
    LeaveType LeaveTypeDTOToLeaveType(LeaveTypeDTO leaveTypeDTO);
}
