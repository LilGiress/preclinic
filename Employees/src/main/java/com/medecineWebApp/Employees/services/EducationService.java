package com.medecineWebApp.Employees.services;

import com.medecineWebApp.Employees.dto.EducationDTO;
import com.medecineWebApp.Employees.models.Education;
import com.medecineWebApp.Employees.models.doctors.Doctor;

import java.util.List;

public interface EducationService {
    List<EducationDTO> getAllEducationsByDoctorId(Doctor doctorId);
    EducationDTO saveEducation(Education education);
    void deleteEducationById(Long id);

}
