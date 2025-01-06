package com.medecineWebApp.Configuration.models;

import com.medecineWebApp.Employees.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private User userId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private LocalDate dateDebutEntreEnFonction;

    @Transient
    private Departement department;
    private String position;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    // One-to-Many relationship with Attendance
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendanceRecords;

    @Transient
    private Set<Roles> roles = new HashSet<>();

}
