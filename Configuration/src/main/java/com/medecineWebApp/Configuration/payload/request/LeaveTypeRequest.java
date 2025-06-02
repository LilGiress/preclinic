package com.medecineWebApp.Configuration.payload.request;


import com.medecineWebApp.Configuration.enums.Status;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypeRequest {
    private String leaveType;
    private Integer leaveDays;
    private Status status;
}
