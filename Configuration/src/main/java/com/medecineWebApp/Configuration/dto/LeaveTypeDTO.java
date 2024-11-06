package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.enums.EntityStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveTypeDTO {
    private Long id;
    private String leaveType;
    private int leaveDays;
    private EntityStatus status;
}
