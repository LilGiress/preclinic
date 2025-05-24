package com.medecineWebApp.Configuration.dto;

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

    private LeaveTypeDTO leaveType;

    private Long employeeId;
}
