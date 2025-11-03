package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.LeaveTypeDTO;
import com.medecineWebApp.Configuration.exception.LeaveTypeNotFoundException;
import com.medecineWebApp.Configuration.mapper.LeaveTypeMapper;
import com.medecineWebApp.Configuration.models.LeaveType;
import com.medecineWebApp.Configuration.payload.request.LeaveTypeRequest;
import com.medecineWebApp.Configuration.repository.leaves.LeaveTypeRepository;
import com.medecineWebApp.Configuration.service.LeaveTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveTypeMapper leaveTypeMapper;

    public LeaveTypeServiceImpl(LeaveTypeRepository leaveTypeRepository, LeaveTypeMapper leaveTypeMapper) {
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveTypeMapper = leaveTypeMapper;
    }

    @Override
    public LeaveTypeDTO save(LeaveTypeRequest leaveTypeRequest) {
        LeaveType leaveType = new LeaveType();
        leaveType.setLeaveType(leaveTypeRequest.getLeaveType());
        leaveType.setLeaveDays(leaveTypeRequest.getLeaveDays());
        leaveType.setStatus(leaveTypeRequest.getStatus());
        return leaveTypeMapper.LeaveTypeToLeaveTypeDTO(leaveTypeRepository.save(leaveType)) ;
    }

    @Override
    public Optional<LeaveTypeDTO> getLeaveTypeById(Long id) {
        return leaveTypeRepository.findById(id).map(leaveTypeMapper::LeaveTypeToLeaveTypeDTO);
    }

    @Override
    public List<LeaveTypeDTO> getAllLeaveTypes() {
        return leaveTypeRepository.findAll().stream().map(leaveTypeMapper::LeaveTypeToLeaveTypeDTO).collect(Collectors.toList());
    }

    @Override
    public LeaveTypeDTO updateLeaveType(Long id, LeaveType leaveType) {
        Optional<LeaveType> leaveTypeOptional = leaveTypeRepository.findById(id);
        LeaveType updatedLeaveType = leaveTypeOptional.get();
        if (leaveTypeOptional.isPresent()) {
            updatedLeaveType.setLeaveType(leaveType.getLeaveType());
            updatedLeaveType.setLeaveDays(leaveType.getLeaveDays());
            updatedLeaveType.setStatus(leaveType.getStatus());
            return leaveTypeMapper.LeaveTypeToLeaveTypeDTO(leaveTypeRepository.save(updatedLeaveType));
        }
         throw new LeaveTypeNotFoundException("Leave Type Not Found");
    }

    @Override
    public void deleteLeaveType(Long id) {
        leaveTypeRepository.deleteById(id);
    }
}
