//package com.medecineWebApp.patients.feignClient;
//
//import com.medecineWebApp.patients.models.externe.Departement;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//@Component
//public class ConfigurationClientFallback implements ConfigurationClient,AppointmentClient,DoctorClient {
//
//    @Override
//    public Departement getDepartementById(Long id) {
//        return new Departement();
//    }
//
//    @Override
//    public List<Map<String, String>> getRegions() {
//        return List.of(Map.of("message", "Service indisponible, réessayez plus tard"));
//    }
//
//    @Override
//    public List<String> getMedicalServices() {
//        return List.of("Service non disponible");
//    }
//
//
//    @Override
//    public Doctor getDoctor(Long id) {
//        System.out.println("⚠ Service Employee indisponible. Retourne un médecin par défaut.");
//        return new Doctor();
//    }
//
//    @Override
//    public List<Map<String, String>> searchDoctors(String region, String city, String specialty) {
//        System.out.println("⚠ Service Employee indisponible. Retourne une liste vide.");
//        return Collections.emptyList();
//    }
//    @Override
//    public Appointment getAppointment(Long id) {
//        System.out.println("⚠ Service Appointment indisponible. Retourne un rendez-vous par défaut.");
//        return new Appointment();
//    }
//}
