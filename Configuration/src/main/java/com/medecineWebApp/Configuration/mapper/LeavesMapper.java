package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.LeavesDTO;
import com.medecineWebApp.Configuration.dto.SalarySettingsDTO;
import com.medecineWebApp.Configuration.models.Leaves;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface LeavesMapper {
    LeavesMapper INSTANCE = Mappers.getMapper(LeavesMapper.class);
    LeavesDTO LeavesToLeavesDTO(Leaves leaves);
    Leaves LeavesDTOToLeaves(LeavesDTO leavesDTO);
    Page<LeavesDTO> LeavesToLeavesDTOPage(Page<Leaves> leavesPage);
    Optional<LeavesDTO> LeavesToLeavesDTOOptional(Optional<Leaves> leaves);
}
