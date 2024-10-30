package com.medecineWebApp.Employees.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Employee  extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeId;
    private LocalDate dateDebutEntreEnFonction;
    private String department;
    private String position;
    private String status;

    // One-to-Many relationship with Attendance
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendanceRecords;

    @Transient
    private String role;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime dateOfCreation;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;


}
