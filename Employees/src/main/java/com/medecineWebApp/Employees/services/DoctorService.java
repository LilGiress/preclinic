package com.medecineWebApp.Employees.services;

import com.medecineWebApp.Employees.dto.DoctorDTO;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import org.springframework.data.domain.Page;


public interface DoctorService {
  DoctorDTO getDoctorById(Long id);
    Page<DoctorDTO> getAllDoctors(String name,int page, int size);
    DoctorDTO saveDoctor(Doctor doctor);
    void deleteDoctor(Long id);
    DoctorDTO updateDoctor(Long id,Doctor doctor);
   DoctorDTO getDoctorWithAssets(Long doctorId);

}
