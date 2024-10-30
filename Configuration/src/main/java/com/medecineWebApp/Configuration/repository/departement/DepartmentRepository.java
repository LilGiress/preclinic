package com.medecineWebApp.Configuration.repository.departement;

import com.medecineWebApp.Configuration.models.Departement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Departement, Long> {
}
