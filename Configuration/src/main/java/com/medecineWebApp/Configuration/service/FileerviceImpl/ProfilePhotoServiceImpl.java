package com.medecineWebApp.Configuration.service.FileerviceImpl;

import com.medecineWebApp.Configuration.service.ProfilePhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ProfilePhotoServiceImpl implements ProfilePhotoService {
    @Value("${app.file.upload-dir}")
    private String uploadDir;

    @Override
    public String storeProfilePhoto(MultipartFile file, Long userId, String role)throws IOException {
        String folder = switch (role.toUpperCase()) {
            case "PATIENT" -> "patients";
            case "MEDECIN" -> "medecins";
            case "EMPLOYE" -> "employes";
            default -> throw new IllegalArgumentException("Rôle non valide !");
        };

        Path profileFolder = Paths.get(uploadDir, "profiles", folder);
        Files.createDirectories(profileFolder);

        String fileName = userId + ".jpg"; // Remplace l’ancienne photo
        Path filePath = profileFolder.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/api/files/profile/" + folder + "/" + fileName;
    }

    @Override
    public Path getProfilePhotoPath(Long userId, String role) {
        String folder = switch (role.toUpperCase()) {
            case "PATIENT" -> "patients";
            case "MEDECIN" -> "medecins";
            case "EMPLOYE" -> "employes";
            default -> throw new IllegalArgumentException("Rôle non valide !");
        };
        return Paths.get(uploadDir, "profiles", folder, userId + ".jpg");
    }

}
