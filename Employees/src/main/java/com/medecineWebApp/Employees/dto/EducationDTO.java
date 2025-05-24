package com.medecineWebApp.Employees.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO extends AuditableDTO{
    private Long id;
    private String degree;
    private String college;
    private int yearOfCompletion;

    @JsonManagedReference
    private DoctorDTO profile;
}
