package com.medecineWebApp.Configuration.models;

import com.medecineWebApp.Configuration.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "config_Leave_type")
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
    private EntityStatus status;


}
