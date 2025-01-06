package com.medecineWebApp.Employees.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "config_Leaves")
public class Leaves extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String leaveReason;


    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    @JoinColumn(name= "user")
    @Transient
    private Employee employee;
}
