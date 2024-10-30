package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.models.Groupe;

import java.util.List;
import java.util.Optional;

public interface GroupeService {
    Groupe create(Groupe groupe);
    List<Groupe> findAllGroupe();
    Optional<Groupe> findGroupeById(Long id);
    Groupe update(Long id,Groupe groupe);
    void delete(Long id);
}
