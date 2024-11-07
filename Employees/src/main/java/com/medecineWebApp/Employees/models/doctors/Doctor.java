package com.medecineWebApp.Employees.models.doctors;

import com.medecineWebApp.Employees.enums.EmployeeStatus;
import com.medecineWebApp.Employees.enums.Gender;
import com.medecineWebApp.Employees.models.Auditable;
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





}
