package com.medecineWebApp.Employees.models.doctors;

import com.medecineWebApp.Employees.enums.EmployeeStatus;
import com.medecineWebApp.Employees.enums.Gender;
import com.medecineWebApp.Employees.models.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Doctors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Doctor extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String doctorBirthday;
    private String imageUrl;
    private String biography;
    private String doctorPostalCode;
    private String doctorCountry;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;

    @Transient
    @Column(nullable = false)
    private Departement department;
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @Transient
    private List<Treatment> treatments;

    @Transient
    private Set<Roles> roles = new HashSet<>();







}
