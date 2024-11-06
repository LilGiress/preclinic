package com.medecineWebApp.Employees.events;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDeletedEvent {
    private Long id;
    private Long UserId;
}
