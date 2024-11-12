package com.medecineWebApp.Configuration.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medecineWebApp.Configuration.models.Region;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CityLoaderService {
    private List<Region> regions;

    @PostConstruct
    public void loadCitiesAndRegions() {
        // Charger le fichier JSON depuis les ressources
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/cities.json")) {
            regions = objectMapper.readValue(inputStream, new TypeReference<List<Region>>() {});
            // Afficher les données chargées (vous pouvez supprimer cette partie)
            regions.forEach(region -> {
                System.out.println("Region: " + region.getRegion());
                region.getCities().forEach(city -> System.out.println("  City: " + city.getName()));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Region> getRegions() {
        return regions;
    }

}
