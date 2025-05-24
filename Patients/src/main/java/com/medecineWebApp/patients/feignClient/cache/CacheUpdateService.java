//package com.medecineWebApp.patients.feignClient.cache;
//
//import com.medecineWebApp.patients.feignClient.AppointmentClient;
//import com.medecineWebApp.patients.feignClient.ConfigurationClient;
//import com.medecineWebApp.patients.feignClient.DoctorClient;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CacheUpdateService {
//    private final ConfigurationClient configurationClient;
//    private final AppointmentClient appointmentClient;
//    private final DoctorClient doctorClient;
//
//    public CacheUpdateService(ConfigurationClient configurationClient, AppointmentClient appointmentClient, DoctorClient doctorClient) {
//        this.configurationClient = configurationClient;
//        this.appointmentClient = appointmentClient;
//        this.doctorClient = doctorClient;
//    }
//    // Supprime le cache et recharge les données toutes les 10 minutes
//    @Scheduled(fixedRate = 600000) // 10 minutes
//    @CacheEvict(value = "regions", allEntries = true)
//    public void refreshRegionsCache() {
//        configurationClient.getRegions();
//        System.out.println("🔄 Cache des régions mis à jour !");
//    }
//
//    @Scheduled(fixedRate = 600000)
//    @CacheEvict(value = "medicalServices", allEntries = true)
//    public void refreshMedicalServicesCache() {
//        configurationClient.getMedicalServices();
//        System.out.println("🔄 Cache des services médicaux mis à jour !");
//    }
//    @Scheduled(fixedRate = 600000) // Rafraîchit toutes les 10 minutes
//    @CacheEvict(value = "doctors", allEntries = true)
//    public void refreshDoctorsCache() {
//        System.out.println("🔄 Cache des médecins vidé !");
//    }
//
//    @Scheduled(fixedRate = 600000)
//    @CacheEvict(value = "searchDoctors", allEntries = true)
//    public void refreshSearchDoctorsCache() {
//        System.out.println("🔄 Cache des résultats de recherche vidé !");
//    }
//
//
//
//}
