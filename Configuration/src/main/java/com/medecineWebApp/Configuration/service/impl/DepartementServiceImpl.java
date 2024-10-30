package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.repository.departement.DepartmentRepository;
import com.medecineWebApp.Configuration.service.DepartementService;
import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DepartementServiceImpl implements DepartementService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Departement createDepartement(Departement department) {
        return departmentRepository.save(department);
    }

    @Override
    public Departement updateDepartement(Long id, Departement department) {
        Optional<Departement> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            Departement updatedDepartment = optionalDepartment.get();
            updatedDepartment.setName(department.getName());
            updatedDepartment.setDescription(department.getDescription());
            updatedDepartment.setActive(department.isActive());
            updatedDepartment.setRoles(department.getRoles());
            return departmentRepository.save(updatedDepartment);
        }
        throw new ResourceNotFoundException("Department not found with id: " + id);
    }

    @Override
    public Optional<Departement> getDepartementById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteDepartement(Long id) {
        departmentRepository.deleteById(id);
    }
}
