package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.LeaveTypeDTO;
import com.medecineWebApp.Configuration.models.LeaveType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveTypeMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    LeaveTypeDTO LeaveTypeToLeaveTypeDTO(LeaveType leaveType);
    @InheritInverseConfiguration
    LeaveType LeaveTypeDTOToLeaveType(LeaveTypeDTO leaveTypeDTO);
}
