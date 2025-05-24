package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.DepartementDTO;
import com.medecineWebApp.Configuration.mapper.DepartementMapper;
import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.repository.departement.DepartmentRepository;
import com.medecineWebApp.Configuration.service.DepartementService;
import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartementServiceImpl implements DepartementService {

    private final DepartmentRepository departmentRepository;
    private  final DepartementMapper departementMapper;

    public DepartementServiceImpl(DepartmentRepository departmentRepository, DepartementMapper departementMapper) {
        this.departmentRepository = departmentRepository;
        this.departementMapper = departementMapper;

    }

    @Override
    public DepartementDTO createDepartement(Departement department) {
        return departementMapper.departementToDepartementDTO(departmentRepository.save(department));
    }

    @Override
    public DepartementDTO updateDepartement(Long id, Departement department) {
        if (id != null && department.getUserId() != null) {
            return departmentRepository.findById(id)
                    .map(departementMapper::departementToDepartementDTO).orElseThrow(
                            () -> new ResourceNotFoundException("Department not found for this id: " + id)
                    );

        }
        throw new ResourceNotFoundException("Department not found with id: " + id);
    }

    @Override
    public Optional<DepartementDTO> getDepartementById(Long id) {
        return departmentRepository.findById(id).map(departementMapper::departementToDepartementDTO);
    }

    @Override
    public List<DepartementDTO> getAllDepartements() {
        //Pageable pageable = PageRequest.of(page, size);
        return departmentRepository.findAll().stream().map(departementMapper::departementToDepartementDTO).collect(Collectors.toList());

    }

    @Override
    public void deleteDepartement(Long id) {
        departmentRepository.deleteById(id);
    }
}
