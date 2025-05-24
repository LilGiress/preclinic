package com.medecineWebApp.Employees.models;

import com.medecineWebApp.Employees.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Employee extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Long addressId;

    private Long cityId;
    private String state;
    private String zip;
    private Long countryId;

    private LocalDate dateDebutEntreEnFonction;
    private Long departmentId;

    private String position;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    // One-to-Many relationship with Attendance
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendanceRecords;
    private Long roleId;

    private Long leaveId;

    private Long invoiceId;


}
