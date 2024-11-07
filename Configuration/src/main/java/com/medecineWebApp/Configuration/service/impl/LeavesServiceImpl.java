package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.LeavesDTO;
import com.medecineWebApp.Configuration.mapper.LeavesMapper;
import com.medecineWebApp.Configuration.models.Leaves;
import com.medecineWebApp.Configuration.payload.request.LeaveRequest;
import com.medecineWebApp.Configuration.repository.leaves.LeavesRepository;
import com.medecineWebApp.Configuration.service.LeaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LeavesServiceImpl implements LeaveService {
    private final LeavesRepository leavesRepository;
    private final LeavesMapper leavesMapper;

    public LeavesServiceImpl(LeavesRepository leavesRepository, LeavesMapper leavesMapper) {
        this.leavesRepository = leavesRepository;
        this.leavesMapper = leavesMapper;
    }

    @Override
    public Page<LeavesDTO> findAllLeaves(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Leaves> leavesPage = leavesRepository.findAll(pageable);
        return leavesMapper.LeavesToLeavesDTOPage(leavesPage);
    }

    @Override
    public Optional<LeavesDTO> findLeaveById(Long id) {
        Optional<Leaves> leaves = leavesRepository.findById(id);
        return leavesMapper.LeavesToLeavesDTOOptional(leaves);
    }

    @Override
    public LeavesDTO save(LeaveRequest leave) {
        Leaves leaves = new Leaves();
        leaves.setStartDate(leave.getStartDate());
        leaves.setEndDate(leave.getEndDate());
        leaves.setLeaveReason(leave.getLeaveReason());
        leaves.setLeaveType(leave.getLeaveType());
        return leavesMapper.LeavesToLeavesDTO(leaves);
    }

    @Override
    public LeavesDTO update(Long id,Leaves leave) {
        Optional<Leaves> optionalLeaves = leavesRepository.findById(id);
        if (optionalLeaves.isPresent()) {
            Leaves updatedLeave = optionalLeaves.get();
            updatedLeave.setLeaveReason(leave.getLeaveReason());
            updatedLeave.setLeaveType(leave.getLeaveType());
            updatedLeave.setStartDate(leave.getStartDate());
            updatedLeave.setEndDate(leave.getEndDate());
            return leavesMapper.LeavesToLeavesDTO(leavesRepository.save(updatedLeave));
        }
        throw new RuntimeException("Leaves with id " + id + " not found");
    }

    @Override
    public void deleteById(Long id) {
        leavesRepository.deleteById(id);
    }
}
