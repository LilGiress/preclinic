package com.medecineWebApp.Configuration.controller;


import com.medecineWebApp.Configuration.models.user.Users;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import com.medecineWebApp.Configuration.service.ProfilePhotoService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/files/profile")
public class ProfilePhotoController {
    private final ProfilePhotoService profilePhotoService;
    private final UserRepository userRepository;

    public ProfilePhotoController(ProfilePhotoService profilePhotoService, UserRepository userRepository) {
        this.profilePhotoService = profilePhotoService;
        this.userRepository = userRepository;
    }

    @PostMapping("/upload/{userId}/{role}")
    public ResponseEntity<String> uploadProfilePhoto(
            @RequestParam("file") MultipartFile file,
            @PathVariable Long userId,
            @PathVariable String role) {
        try {
            String photoUrl = profilePhotoService.storeProfilePhoto(file, userId, role);

            // Mise à jour en base de données
            Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
            user.setPhotoUrl(photoUrl);
            userRepository.save(user);

            return ResponseEntity.ok(photoUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'upload");
        }
    }

    @GetMapping("/{role}/{userId}.jpg")
    public ResponseEntity<Resource> getProfilePhoto(@PathVariable Long userId, @PathVariable String role) {
        Path filePath = profilePhotoService.getProfilePhotoPath(userId, role);
        if (!Files.exists(filePath)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        try {
            Resource file = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(file);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
