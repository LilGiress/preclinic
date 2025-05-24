package com.medecineWebApp.Configuration.service.FileerviceImpl;

import com.medecineWebApp.Configuration.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {
   // private final Path fileStorageLocation;
    @Value("${file.storage.location}")
    private Resource fileResource;
    //   String fileUploadPath;

    @Value("${app.file.upload-dir}")
    private String uploadDir;


    @Override
    public String storeFile(MultipartFile file, Long patientId,String category) throws IOException {
        /*String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, "patient_" + patientId, fileName);

        Files.createDirectories(filePath.getParent()); // Crée le dossier s'il n'existe pas
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();*/

        // Définition du chemin du patient et du type de document
        Path patientFolder = Paths.get(uploadDir, "patients", "patient_" + patientId);
        Path categoryFolder = patientFolder.resolve(category);

        // Création des dossiers si inexistants
        Files.createDirectories(categoryFolder);

        // Sauvegarde du fichier avec un identifiant unique
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = categoryFolder.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();

    }

    @Override
    public Resource loadFileAsResource(String filePath) throws MalformedURLException, FileNotFoundException {
        Path path = Paths.get(filePath);
        Resource resource = new UrlResource(path.toUri());
        if (resource.exists()) {
            return resource;
        } else {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    @Override
    public String storeFileForPatient(MultipartFile file, Long patientId, String category) throws IOException {
        Path patientFolder = Paths.get(uploadDir, "patients", "patient_" + patientId);
        Path categoryFolder = patientFolder.resolve(category);
        return saveFile(file, categoryFolder);
    }

    @Override
    public String storeFileForDoctor(MultipartFile file, Long doctorId, String category) throws IOException {
        Path doctorFolder = Paths.get(uploadDir, "medecins", "doctor_" + doctorId);
        Path categoryFolder = doctorFolder.resolve(category);
        return saveFile(file, categoryFolder);
    }

    @Override
    public String storeFileForManagement(MultipartFile file, String category) throws IOException {
        Path managementFolder = Paths.get(uploadDir, "gestion", category);
        return saveFile(file, managementFolder);
    }

    @Override
    public String createFolder(Long userId, String role, String folderName) throws IOException {
        String folderType = switch (role.toUpperCase()) {
            case "PATIENT" -> "patients";
            case "MEDECIN" -> "medecins";
            case "EMPLOYE", "ADMIN" -> "gestion";
            default -> throw new IllegalArgumentException("Rôle invalide !");
        };

        Path userFolder = Paths.get(uploadDir, folderType, String.valueOf(userId), folderName);

        if (!Files.exists(userFolder)) {
            Files.createDirectories(userFolder);
        } else {
            throw new IOException("Le dossier existe déjà !");
        }

        return userFolder.toString();

    }

    @Override
    // 🔹 Lister les dossiers d'un utilisateur
    public List<String> listFolders(Long userId, String role) throws IOException {
        String folderType = switch (role.toUpperCase()) {
            case "PATIENT" -> "patients";
            case "MEDECIN" -> "medecins";
            case "EMPLOYE", "ADMIN" -> "gestion";
            default -> throw new IllegalArgumentException("Rôle invalide !");
        };

        Path userFolder = Paths.get(uploadDir, folderType, String.valueOf(userId));

        if (!Files.exists(userFolder)) {
            throw new IOException("Dossier utilisateur introuvable !");
        }

        try (Stream<Path> paths = Files.list(userFolder)) {
            return paths
                    .filter(Files::isDirectory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }
    }

    @Override
    // 🔹 Supprimer un dossier et son contenu
    public String deleteFolder(Long userId, String role, String folderName) throws IOException {
        String folderType = switch (role.toUpperCase()) {
            case "PATIENT" -> "patients";
            case "MEDECIN" -> "medecins";
            case "EMPLOYE", "ADMIN" -> "gestion";
            default -> throw new IllegalArgumentException("Rôle invalide !");
        };

        Path folderPath = Paths.get(uploadDir, folderType, String.valueOf(userId), folderName);

        if (!Files.exists(folderPath)) {
            throw new IOException("Dossier non trouvé !");
        }

        // Supprime récursivement tout le contenu
        Files.walk(folderPath)
                .sorted(Comparator.reverseOrder()) // Supprime d'abord les fichiers avant le dossier
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return "Dossier supprimé : " + folderPath.toString();

    }

    private String saveFile(MultipartFile file, Path directory) throws IOException {
        Files.createDirectories(directory);
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = directory.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return filePath.toString();
    }
































   /* public FileStorageServiceImpl(@Value("${file.storage.location}")String fileStorageLocation) {
        this.fileStorageLocation = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    */

   /* @Override
    public String saveFile(
                           @Nonnull Long userId , @NonNull MultipartFile sourceFile) {*/
      //  final String fileUploadSubPath = fileUploadPath + separator + userId;



       /* String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Check for invalid characters in the file name
            if(fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy the file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }*/


      /*  return uploadFile(sourceFile);
    }*/

   /* @Override
    public String uploadFile( @NonNull MultipartFile sourceFile) {



          String fileName = StringUtils.cleanPath(Objects.requireNonNull(sourceFile.getOriginalFilename()));
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath = fileName + separator + currentTimeMillis()+ fileExtension;

        try {
            // Check for invalid characters in the file name
            if(fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            String s = Files.readString(Path.of(fileResource.getFilename()));
            if (s.equalsIgnoreCase(String.valueOf(targetLocation))){
                // Copy the file to the target location (Replacing existing file with the same name)
                //Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(sourceFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }else {
                Path targetPath = Paths.get(targetFilePath);
                try {
                    Files.write(targetPath,sourceFile.getBytes());
                    log.info("File saved to: " + targetFilePath);
                    return targetFilePath;
                } catch (IOException e) {
                    // throw new RuntimeException(e);
                    log.error("File was not saved", e);
                }
            }



            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
        */



       /* final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
           boolean folderCreated = targetFolder.mkdirs();
           if (!folderCreated) {
               log.warn("Failed to create the target folder: " + targetFolder);
               return null;
           }
        }
           final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
           String targetFilePath = finalUploadPath + separator + currentTimeMillis()+ fileExtension;
           Path targetPath = Paths.get(targetFilePath);
           try {
               Files.write(targetPath,sourceFile.getBytes());
               log.info("File saved to: " + targetFilePath);
               return targetFilePath;
           } catch (IOException e) {
              // throw new RuntimeException(e);
               log.error("File was not saved", e);
           }

        return "";*/
  //  }

   /* @Override
    public Boolean deleteFile(String fileName) {
        try {
            Path filePath = loadFile(fileName);
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file " + fileName + ". Please try again!", e);
        }
    }
    */

  /*  @Override
    public String getFileExtension(String fileName) {
        if (fileName ==null || fileName.isEmpty()) {
            return  "";
        }
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return fileName.substring(lastIndexOf+1).toLowerCase();
        }

    @Override
    public Path loadFile(String fileName) {
        return this.fileStorageLocation.resolve(fileName).normalize();
    }
*/

}