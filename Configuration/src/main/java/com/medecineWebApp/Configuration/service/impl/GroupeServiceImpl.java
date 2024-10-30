package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.models.Groupe;
import com.medecineWebApp.Configuration.repository.groupe.GroupeRepository;
import com.medecineWebApp.Configuration.service.GroupeService;
import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class GroupeServiceImpl implements GroupeService {
    @Autowired
    private GroupeRepository groupeRepository;
    @Override
    public Groupe create(Groupe groupe) {
        return groupeRepository.save(groupe);
    }

    @Override
    public List<Groupe> findAllGroupe() {
        return groupeRepository.findAll();
    }

    @Override
    public Optional<Groupe> findGroupeById(Long id) {
        return groupeRepository.findById(id);
    }

    @Override
    public Groupe update(Long id, Groupe groupe) {
        Optional<Groupe> groupeOptional = groupeRepository.findById(id);
        if (groupeOptional.isPresent()) {
            Groupe updatedGroupe = groupeOptional.get();
           // updatedGroupe.setServices(groupe.getServices());
            updatedGroupe.setNom(groupe.getNom());
            updatedGroupe.setDescription(groupe.getDescription());
            updatedGroupe.setDepartement(groupe.getDepartement());
            updatedGroupe.setUsers(groupe.getUsers());
            return groupeRepository.save(updatedGroupe);
        }
        throw new ResourceNotFoundException("Groupe with id " + id + " not found");
    }

    @Override
    public void delete(Long id) {
    groupeRepository.deleteById(id);
    }
}
