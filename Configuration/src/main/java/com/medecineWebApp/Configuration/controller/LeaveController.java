package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.LeavesDTO;
import com.medecineWebApp.Configuration.enums.LeaveStatus;
import com.medecineWebApp.Configuration.models.Leaves;
import com.medecineWebApp.Configuration.payload.request.LeaveRequest;
import com.medecineWebApp.Configuration.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("/create")
    public ResponseEntity<LeavesDTO> createLeaves(@RequestBody LeaveRequest leaveRequest) {
        log.warn("-------------------------"+leaveRequest.getLeaveReason());
        return ResponseEntity.ok(leaveService.save(leaveRequest));


    }

    @GetMapping("/search")
    public ResponseEntity <Page<LeavesDTO>> getAllLeaves(
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) LeaveStatus status,
            @RequestParam(required = false) String startFrom,
            @RequestParam(required = false) String endBefore,
            @RequestParam(required = false) Long leaveTypeId,
            @PageableDefault(page = 0, size = 10, sort = "startDate", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        LocalDate start = (startFrom != null) ? LocalDate.parse(startFrom) : null;
        LocalDate end = (endBefore != null) ? LocalDate.parse(endBefore) : null;
        return ResponseEntity.ok(leaveService.findAllLeaves(employeeId, status, start, end, leaveTypeId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LeavesDTO>>getLeaveById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.findLeaveById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeavesDTO> updateLeaveById(@PathVariable Long id, @RequestBody Leaves leaves) {
        return ResponseEntity.ok(leaveService.update(id, leaves));
    }

    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<Boolean> changeStatus(@PathVariable Long id, @RequestBody String status){
        return ResponseEntity.ok(leaveService.changeStatus(id,status));
    }

    @DeleteMapping("/{id}")
    public void deleteLeaveById(@PathVariable Long id) {
        leaveService.deleteById(id);
    }
}
