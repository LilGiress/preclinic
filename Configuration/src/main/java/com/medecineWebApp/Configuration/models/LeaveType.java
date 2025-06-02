package com.medecineWebApp.Configuration.models;

import com.medecineWebApp.Configuration.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Leave_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveType extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String leaveType;
    @Column(nullable = false)
    private int leaveDays;
    @Enumerated(EnumType.STRING)
    private Status status;


}
