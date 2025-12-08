package com.medecineWebApp.Configuration.payload.request;

import com.medecineWebApp.Configuration.enums.LeaveStatus;
import com.medecineWebApp.Configuration.models.LeaveType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;


import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {
    private LocalDate startDate;

    private LocalDate endDate;

    private String leaveReason;
    private Long numberOfDays;

    private LeaveType leaveType;

    private Long RemainingLeave;

    private Long employeeId;

    private LeaveStatus status;
}
