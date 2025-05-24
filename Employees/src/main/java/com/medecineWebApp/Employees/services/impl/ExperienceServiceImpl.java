package com.medecineWebApp.Employees.services.impl;

import com.medecineWebApp.Employees.dto.ExperienceDTO;
import com.medecineWebApp.Employees.mapper.ExperienceMapper;
import com.medecineWebApp.Employees.models.Experience;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import com.medecineWebApp.Employees.repository.ExperienceRepository;
import com.medecineWebApp.Employees.services.ExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository, ExperienceMapper experienceMapper) {
        this.experienceRepository = experienceRepository;
        this.experienceMapper = experienceMapper;
    }

    @Override
    public List<ExperienceDTO> getAllExperiencesByDoctorId(Doctor doctorId) {
        return experienceRepository.findByProfile(doctorId).stream()
                .map(experienceMapper::toExperience)
                .toList();
    }

    @Override
    public ExperienceDTO saveExperience(Experience experience) {
        return experienceMapper.toExperience(experienceRepository.save(experience));
    }

    @Override
    public void deleteExperience(Long experienceId) {
        experienceRepository.deleteById(experienceId);
    }
}
