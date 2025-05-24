package com.medecineWebApp.Employees.services.impl;

import com.medecineWebApp.Employees.dto.DoctorDTO;
import com.medecineWebApp.Employees.feignClient.AssetClient;
import com.medecineWebApp.Employees.feignClient.DepartmentClient;
import com.medecineWebApp.Employees.feignClient.TreatmentClient;
import com.medecineWebApp.Employees.mapper.DoctorMapper;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import com.medecineWebApp.Employees.repository.DoctorRepository;
import com.medecineWebApp.Employees.services.DoctorService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private  final DoctorMapper doctorMapper;
    private final TreatmentClient treatmentClient;
    private final DepartmentClient departmentClient;
    private final AssetClient assetClient;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper, TreatmentClient treatmentClient, DepartmentClient departmentClient, AssetClient assetClient) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.treatmentClient = treatmentClient;
        this.departmentClient = departmentClient;
        this.assetClient = assetClient;
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        if (id != null) {
            return doctorRepository.findById(id).map(
                    doctorMapper::doctorToDTO
            ).orElseThrow(
                    () -> new ResourceNotFoundException("doctor not found for this id: " + id)
            );
        }throw new RuntimeException("Doctor not found with id " + id);

    }

    @Override
    public Page<DoctorDTO> getAllDoctors(String name,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return doctorRepository.findAll(pageable).map(
                doctorMapper::doctorToDTO
        );
    }

    @Override
    public DoctorDTO saveDoctor(Doctor doctor) {
        return doctorMapper.doctorToDTO(doctorRepository.save(doctor));
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public DoctorDTO updateDoctor(Long id, Doctor doctor) {
      //  Treatment treatment = treatmentClient.getTreatment(doctor.getTreatments().get(0).getId());
      //  Departement departement = departmentClient.getDepartment(doctor.getDepartment().getId());
        if (id !=null && doctor.getTreatmentId() != null || doctor.getDepartmentId() != null) {
//            doctor.setDepartment(departement);
//            doctor.setTreatments(List.of(treatment));
            return doctorMapper.doctorToDTO(doctorRepository.save(doctor));
        }
        throw new RuntimeException("Doctor not found with id " + id);
    }

    @Override
    public DoctorDTO getDoctorWithAssets(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException("doctor not found for this id: " + doctorId)
        );
//        List<AssetDTO> assets = assetClient.getAssetsByDoctor(doctorId);
//        doctor.setAssets(assets);


        return doctorMapper.doctorToDTO(doctor);
    }
}
