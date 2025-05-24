//package com.medecineWebApp.patients.service;
//
//
//import com.medecineWebApp.patients.feignClient.ConfigurationClient;
//import com.medecineWebApp.patients.feignClient.DoctorClient;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class SearchService {
//    private final ConfigurationClient configurationClient;
//
//    public SearchService(ConfigurationClient configurationClient) {
//        this.configurationClient = configurationClient;
//    }
//
//    public Map<String, Object> search(String region, String city, String service) {
//        Map<String, Object> result = new HashMap<>();
//
//        // Récupérer les services médicaux et régions
//        List<Map<String, String>> regions = configurationClient.getRegions();
//        List<String> services = configurationClient.getMedicalServices();
//
//        // Vérifier si la région et la ville existent
//        boolean regionExists = regions.stream().anyMatch(r -> r.get("name").equalsIgnoreCase(region));
//        boolean cityExists = regions.stream().anyMatch(r -> r.get("cities").contains(city));
//        boolean serviceExists = services.contains(service);
//
//        if (!regionExists || !cityExists || !serviceExists) {
//            result.put("error", "Région, ville ou service invalide.");
//            return result;
//        }
//
//        // Récupérer la liste des docteurs pour cette région et service
//        List<Map<String, String>> doctors = doctorClient.searchDoctors(region, city, service);
//        result.put("doctors", doctors);
//
//        return result;
//    }
//}
