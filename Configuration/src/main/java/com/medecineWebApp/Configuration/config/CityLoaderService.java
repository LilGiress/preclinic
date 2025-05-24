package com.medecineWebApp.Configuration.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medecineWebApp.Configuration.models.Address;
import com.medecineWebApp.Configuration.models.City;
import com.medecineWebApp.Configuration.models.Country;
import com.medecineWebApp.Configuration.models.Region;
import com.medecineWebApp.Configuration.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;


@Component
public class CityLoaderService {

    private final CountryRepository countryRepository;

    public CityLoaderService( CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    @Transactional
    public void loadCitiesAndRegions() {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Country>> typeReference = new TypeReference<>() {};

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/regions_cameroun.json");
        if (inputStream == null) {
            System.out.println("❌ ERREUR : Impossible de charger le fichier JSON !");
            return;
        }else {
            System.out.println("✅ Fichier trouvé !");
        }

        try {

            List<Country> countries = mapper.readValue(inputStream, typeReference);

            // Associer chaque région, ville et adresse à son parent avant de sauvegarder
            for (Country country : countries) {
                for (Region region : country.getRegions()) {
                    region.setCountry(country);

                    for (City city : region.getVilles()) {
                        city.setRegion(region);

                        for (Address address : city.getAddresses()) {
                            address.setCity(city); // Association adresse -> ville
                        }
                    }
                }
            }



            System.out.println("✅ je suis ici!"+countries.get(0).getName());
            countryRepository.saveAll(countries);
            System.out.println("✅ Données chargées avec succès !");
        } catch (Exception e) {
            System.out.println("❌ Erreur lors du chargement des données : " + e.getMessage());
        }

   }

}
