package com.medecineWebApp.Employees.models.doctors;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.List;

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
    private Long countryId;
    private Long addressId;
    private Long employeeId;
    private Long roleId;
    private Long departmentId;
   private Long treatmentId;
   private Long patientId;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String pricing;
    private String phone;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<DoctorSchedule> doctorSchedule;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Education> education;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Experience> experience;

    @ElementCollection
    private List<String> services;

    private Long assets;


}
