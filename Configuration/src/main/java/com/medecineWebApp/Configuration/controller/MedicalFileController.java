package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.models.MedicalFile;
import com.medecineWebApp.Configuration.repository.MedicalFileRepository;
import com.medecineWebApp.Configuration.service.FileerviceImpl.FileStorageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medicalfiles")
public class MedicalFileController {
    @Value("${app.file.upload-dir}")
    private String uploadDir;
    private  final FileStorageServiceImpl fileStorageService;
    private final MedicalFileRepository medicalFileRepository;

    public MedicalFileController(FileStorageServiceImpl fileStorageService, com.medecineWebApp.Configuration.repository.MedicalFileRepository medicalFileRepository) {
        this.fileStorageService = fileStorageService;
        this.medicalFileRepository = medicalFileRepository;
    }

   /* @PostMapping("/upload/{patientId}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long patientId) {
        try {
            String filePath = fileStorageService.storeFile(file, patientId);
            MedicalFile medicalFile = new MedicalFile();
            medicalFile.setFileName(file.getOriginalFilename());
            medicalFile.setFileType(file.getContentType());
            medicalFile.setFilePath(filePath);
            medicalFileRepository.save(medicalFile);

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed!");
        }
    }*/

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws MalformedURLException, FileNotFoundException {
        MedicalFile medicalFile = medicalFileRepository.findById(fileId).orElseThrow();
        Resource file = fileStorageService.loadFileAsResource(medicalFile.getFilePath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(medicalFile.getFileType()))
                .body(file);
    }

    @PostMapping("/upload/{patientId}/{category}")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable Long patientId,
            @PathVariable String category) {
        try {
            String filePath = fileStorageService.storeFile(file, patientId, category);
            return ResponseEntity.ok("Fichier enregistré à : " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'upload");
        }
    }

    @GetMapping("/list/{patientId}/{category}")
    public ResponseEntity<List<String>> listFiles(
            @PathVariable Long patientId,
            @PathVariable String category) {
        Path categoryFolder = Paths.get(uploadDir, "patients", "patient_" + patientId, category);

        if (!Files.exists(categoryFolder)) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        try {
            List<String> fileNames = Files.list(categoryFolder)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fileNames);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }


    @PostMapping("/upload/doctor/{doctorId}/{category}")
    public ResponseEntity<String> uploadDoctorFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable Long doctorId,
            @PathVariable String category) {
        try {
            String filePath = fileStorageService.storeFileForDoctor(file, doctorId, category);
            return ResponseEntity.ok("Fichier enregistré à : " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'upload");
        }
    }

    @PostMapping("/upload/management/{category}")
    public ResponseEntity<String> uploadManagementFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable String category) {
        try {
            String filePath = fileStorageService.storeFileForManagement(file, category);
            return ResponseEntity.ok("Fichier enregistré à : " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'upload");
        }
    }

    @GetMapping("/list/doctor/{doctorId}/{category}")
    public ResponseEntity<List<String>> listDoctorFiles(@PathVariable Long doctorId, @PathVariable String category) {
        return listFiles(Paths.get("/opt/medical-files/medecins/doctor_" + doctorId, category));
    }

    @GetMapping("/list/management/{category}")
    public ResponseEntity<List<String>> listManagementFiles(@PathVariable String category) {
        return listFiles(Paths.get("/opt/medical-files/gestion", category));
    }

    private ResponseEntity<List<String>> listFiles(Path directory) {
        if (!Files.exists(directory)) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        try {
            List<String> fileNames = Files.list(directory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fileNames);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @PostMapping("/create/{userId}/{role}")
    public ResponseEntity<String> createFolder(
            @PathVariable Long userId,
            @PathVariable String role,
            @RequestParam String folderName) {
        try {
            String folderPath = fileStorageService.createFolder(userId, role, folderName);
            return ResponseEntity.ok("Dossier créé à : " + folderPath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la création du dossier");
        }
    }

    // 🔹 Endpoint pour lister les dossiers d'un utilisateur
    @GetMapping("/list/{userId}/{role}")
    public ResponseEntity<?> listFolders(@PathVariable Long userId, @PathVariable String role) {
        try {
            List<String> folders = fileStorageService.listFolders(userId, role);
            return ResponseEntity.ok(folders);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur : " + e.getMessage());
        }
    }

    // 🔹 Endpoint pour supprimer un dossier
    @DeleteMapping("/delete/{userId}/{role}")
    public ResponseEntity<?> deleteFolder(
            @PathVariable Long userId,
            @PathVariable String role,
            @RequestParam String folderName) {
        try {
            String result = fileStorageService.deleteFolder(userId, role, folderName);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur : " + e.getMessage());
        }
    }
}
