package com.medecineWebApp.Employees.services;

import com.medecineWebApp.Employees.dto.ExperienceDTO;
import com.medecineWebApp.Employees.models.Experience;
import com.medecineWebApp.Employees.models.doctors.Doctor;

import java.util.List;

public interface ExperienceService {
    List<ExperienceDTO> getAllExperiencesByDoctorId(Doctor doctorId);
    ExperienceDTO saveExperience(Experience experience);
    void deleteExperience(Long experienceId);
}
