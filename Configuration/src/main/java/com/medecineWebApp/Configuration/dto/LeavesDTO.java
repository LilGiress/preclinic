package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.enums.LeaveStatus;
import com.medecineWebApp.Configuration.models.LeaveType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeavesDTO extends AuditableDTO {
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String leaveReason;
    private Long numberOfDays;
    private LeaveTypeDTO leaveType;

    private Long employeeId;

    private Long RemainingLeave;

    private LeaveStatus status;
}
