package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.Specifications.LeaveSpecifications;
import com.medecineWebApp.Configuration.dto.LeavesDTO;
import com.medecineWebApp.Configuration.enums.LeaveStatus;
import com.medecineWebApp.Configuration.exception.LeaveNotFoundException;
import com.medecineWebApp.Configuration.mapper.LeavesMapper;
import com.medecineWebApp.Configuration.models.Leaves;
import com.medecineWebApp.Configuration.payload.request.LeaveRequest;
import com.medecineWebApp.Configuration.repository.leaves.LeavesRepository;
import com.medecineWebApp.Configuration.service.LeaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    public Page<LeavesDTO> findAllLeaves(Long employeeId, LeaveStatus status,
                                         LocalDate startFrom, LocalDate endBefore,
                                         Long leaveTypeId, Pageable pageable) {
        Specification<Leaves> spec = Specification
                .where(LeaveSpecifications.hasEmployeeId(employeeId))
                .and(LeaveSpecifications.hasStatus(status))
                .and(LeaveSpecifications.startsAfter(startFrom))
                .and(LeaveSpecifications.endsBefore(endBefore))
                .and(LeaveSpecifications.hasLeaveType(leaveTypeId));
       // Pageable pageable = PageRequest.of(page, size);
        return leavesRepository.findAll(spec,pageable).map(leavesMapper::LeavesToLeavesDTO);

    }

    @Override
    public Optional<LeavesDTO> findLeaveById(Long id) {
        return leavesRepository.findById(id).map(leavesMapper::LeavesToLeavesDTO);
    }

    @Override
    public LeavesDTO save(LeaveRequest leave) {
        Leaves leaves = new Leaves();
        leaves.setStartDate(leave.getStartDate());
        leaves.setEndDate(leave.getEndDate());
        leaves.setLeaveReason(leave.getLeaveReason());
        leaves.setLeaveType(leave.getLeaveType());
        return leavesMapper.LeavesToLeavesDTO(leavesRepository.save(leaves));
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
        throw new LeaveNotFoundException("Leaves with id " + id + " not found");
    }

    @Override
    public void deleteById(Long id) {
        leavesRepository.deleteById(id);
    }


//    public Leaves save(Leaves leave) {
//        // Exemple : quota de 30 jours
//        int annualQuota = 30;
//
//        // Calcul du nombre de jours demandés
//        long daysRequested = ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1;
//
//        // Total des congés déjà approuvés cette année
//        List<Leaves> approvedLeaves = leavesRepository.findAll().stream()
//                .filter(l -> l.getEmployeeId().equals(leave.getEmployeeId()))
//                .filter(l -> l.getStatus() == LeaveStatus.APPROVED)
//                .filter(l -> l.getStartDate().getYear() == LocalDate.now().getYear())
//                .toList();
//
//        long totalApprovedDays = approvedLeaves.stream()
//                .mapToLong(l -> ChronoUnit.DAYS.between(l.getStartDate(), l.getEndDate()) + 1)
//                .sum();
//
//        // Calcul du reste
//        long remainingLeave = annualQuota - totalApprovedDays;
//
//        // Si la demande dépasse le solde → refuser ou lever une exception
//        if (daysRequested > remainingLeave) {
//            throw new IllegalArgumentException("Pas assez de jours de congés restants !");
//        }
//
//        leave.setRemainingLeave(remainingLeave - daysRequested);
//
//        return leavesRepository.save(leave);
//    }
}
