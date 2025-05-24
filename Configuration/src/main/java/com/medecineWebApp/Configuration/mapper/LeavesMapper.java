package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.LeavesDTO;
import com.medecineWebApp.Configuration.models.Leaves;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeavesMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    LeavesDTO LeavesToLeavesDTO(Leaves leaves);
    @InheritInverseConfiguration
    Leaves LeavesDTOToLeaves(LeavesDTO leavesDTO);

}
