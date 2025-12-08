package com.medecineWebApp.Configuration.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medecineWebApp.Configuration.enums.EntityStatus;
import com.medecineWebApp.Configuration.models.LeaveType;
import com.medecineWebApp.Configuration.repository.leaves.LeaveTypeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LeaveTypeInitializer  {
    private final LeaveTypeRepository leaveTypeRepository;

@PostConstruct
    public void init()  {
        // Exécuter seulement si la table est vide
        try {
            // Ne rien importer si déjà existant
            if (leaveTypeRepository.count() > 0) {
                System.out.println("📌 LeaveType already initialized — no import required");
                return;
            }
            System.out.println("🚀 Importing default leave types...");

            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<LeaveType>> typeRef = new TypeReference<>() {};

            InputStream inputStream = new ClassPathResource("data/leave_type_data.json").getInputStream();
            List<LeaveType> leaveTypes = mapper.readValue(inputStream, typeRef);

            leaveTypes.forEach(t -> t.setStatus(EntityStatus.ACTIVE));
            leaveTypeRepository.saveAll(leaveTypes);

            System.out.println("✅ Leave types successfully imported ✔");
        } catch (Exception e) {
            System.err.println("❌ Error importing leave types: " + e.getMessage());
        }

    }
}
