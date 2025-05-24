package com.medecineWebApp.Employees.services.impl;

import com.medecineWebApp.Employees.dto.EducationDTO;
import com.medecineWebApp.Employees.mapper.EducationMapper;
import com.medecineWebApp.Employees.models.Education;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import com.medecineWebApp.Employees.repository.EducationRepository;
import com.medecineWebApp.Employees.services.EducationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;

    public EducationServiceImpl(EducationRepository educationRepository, EducationMapper educationMapper) {
        this.educationRepository = educationRepository;
        this.educationMapper = educationMapper;
    }

    @Override
    public List<EducationDTO> getAllEducationsByDoctorId(Doctor doctorId) {
        return educationRepository.findByProfile(doctorId).stream()
                .map(educationMapper::toEducation)
                .toList();

    }

    @Override
    public EducationDTO saveEducation(Education education) {
        return educationMapper.toEducation(educationRepository.save(education));
    }

    @Override
    public void deleteEducationById(Long id) {
        educationRepository.deleteById(id);
    }
}
