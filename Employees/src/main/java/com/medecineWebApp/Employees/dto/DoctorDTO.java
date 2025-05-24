package com.medecineWebApp.Employees.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medecineWebApp.Employees.enums.EmployeeStatus;
import com.medecineWebApp.Employees.enums.Gender;
import com.medecineWebApp.Employees.models.*;
import com.medecineWebApp.Employees.models.doctors.DoctorSchedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO extends AuditableDTO {
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

    private List<DoctorScheduleDTO> doctorSchedule;

    private List<AppointmentDTO> appointments;

    private List<ReviewDTO> reviews;

    private List<MedicalRecordDTO> medicalRecords;

    @JsonManagedReference
    private List<EducationDTO> education;

    @JsonManagedReference
    private List<ExperienceDTO> experience;

    @ElementCollection
    private List<String> services;

}
