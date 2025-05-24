package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FileController {

   /* private final FileStorageService fileStorageService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("userId") Long userId) {
        String fileName = fileStorageService.saveFile(userId,file);
        return "File uploaded successfully: " + fileName;
    }

    @DeleteMapping("/delete/{fileName}")
    public String deleteFile(@PathVariable String fileName) {
        boolean deleted = fileStorageService.deleteFile(fileName);
        return deleted ? "File deleted successfully: " + fileName : "File not found: " + fileName;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = fileStorageService.loadFile(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .header("File-Name",fileName)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }*/

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // 1. Vérifier la taille (max 5 Mo)
            if (file.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("⛔ Taille trop grande (> 5 Mo)");
            }

            // 2. Vérifier le type MIME
            String contentType = file.getContentType();
            List<String> allowedTypes = List.of("image/jpeg", "image/png", "application/pdf");

            if (!allowedTypes.contains(contentType)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("⛔ Type non autorisé : " + contentType);
            }

            // 3. Enregistrer le fichier
            File dir = new File("uploads/");
            if (!dir.exists()) dir.mkdirs();

            Path path = Paths.get("uploads/" + file.getOriginalFilename());
            Files.write(path, file.getBytes());

            return ResponseEntity.ok("✅ Fichier reçu : " + file.getOriginalFilename());

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Erreur : " + e.getMessage());
        }
    }

}
