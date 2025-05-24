package com.medecineWebApp.Configuration.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medecineWebApp.Configuration.models.*;
import com.medecineWebApp.Configuration.repository.departement.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DepartementDataLoader {
    private final DepartmentRepository departmentRepository;

    public DepartementDataLoader(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @PostConstruct
    @Transactional
    public void loadDepartementData() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Departement>> typeReference = new TypeReference<>() {};

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/departements.json");
        if (inputStream == null) {
            System.out.println("❌ ERREUR : Impossible de charger le fichier JSON !");
            return;
        }else {
            System.out.println("✅ Fichier trouvé !");
        }

        try {

            List<Departement> departements = mapper.readValue(inputStream, typeReference);

            // Associer chaque région, ville et adresse à son parent avant de sauvegarder
            for (Departement departement : departements) {
                for (Services service : departement.getServices()) {
                    service.setDepartement(departement);
                }
            }
            departmentRepository.saveAll(departements);
            System.out.println("Importation departement réussie !");



            System.out.println("✅ je suis ici!"+departements.get(0).getName());
//            countryRepository.saveAll(countries);
            System.out.println("✅ Données chargées avec succès !");
        } catch (Exception e) {
            System.out.println("❌ Erreur lors du chargement des données du departement: " + e.getMessage());
        }
    }
}
