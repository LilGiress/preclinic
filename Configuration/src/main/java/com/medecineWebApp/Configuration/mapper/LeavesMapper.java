package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.LeavesDTO;
import com.medecineWebApp.Configuration.models.Leaves;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeavesMapper {
    LeavesDTO LeavesToLeavesDTO(Leaves leaves);
    @InheritInverseConfiguration
    Leaves LeavesDTOToLeaves(LeavesDTO leavesDTO);

}
