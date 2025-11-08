package com.medecineWebApp.Configuration.models;

import com.medecineWebApp.Configuration.enums.LeaveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Leaves")
@EntityListeners(AuditingEntityListener.class)
public class Leaves extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String leaveReason;
    private Long RemainingLeave;

    private Long employeeId;


    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;
    private LeaveStatus status;

}
