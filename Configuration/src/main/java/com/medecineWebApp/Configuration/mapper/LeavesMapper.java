package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.LeavesDTO;
import com.medecineWebApp.Configuration.models.Leaves;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeavesMapper {
    LeavesMapper INSTANCE = Mappers.getMapper(LeavesMapper.class);
    LeavesDTO LeavesToLeavesDTO(Leaves leaves);
    Leaves LeavesDTOToLeaves(LeavesDTO leavesDTO);
}
