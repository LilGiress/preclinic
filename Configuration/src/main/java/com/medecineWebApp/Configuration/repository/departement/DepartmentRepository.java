package com.medecineWebApp.Configuration.repository.departement;

import com.medecineWebApp.Configuration.models.Departement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Departement, Long> {
    List<Departement> findAllByIdIn(Collection<Long> ids);
}
