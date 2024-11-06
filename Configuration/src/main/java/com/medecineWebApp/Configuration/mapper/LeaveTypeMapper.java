package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.LeaveTypeDTO;
import com.medecineWebApp.Configuration.models.LeaveType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveTypeMapper {
    LeaveTypeMapper INSTANCE = Mappers.getMapper(LeaveTypeMapper.class);
    LeaveTypeDTO LeaveTypeToLeaveTypeDTO(LeaveType leaveType);
    LeaveType LeaveTypeDTOToLeaveType(LeaveTypeDTO leaveTypeDTO);
}
