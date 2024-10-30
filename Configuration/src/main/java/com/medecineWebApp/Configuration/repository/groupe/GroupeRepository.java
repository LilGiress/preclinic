package com.medecineWebApp.Configuration.repository.groupe;

import com.medecineWebApp.Configuration.models.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
}
