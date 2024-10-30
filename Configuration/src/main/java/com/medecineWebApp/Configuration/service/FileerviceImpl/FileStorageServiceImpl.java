package com.medecineWebApp.Configuration.service.FileerviceImpl;

import com.medecineWebApp.Configuration.service.FileStorageService;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import reactor.util.annotation.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import static java.io.File.separator;
import static java.lang.System.currentTimeMillis;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {
    private final Path fileStorageLocation;
    @Value("${file.storage.location}")
    private Resource fileResource;
 //   String fileUploadPath;

    public FileStorageServiceImpl(@Value("${file.storage.location}")String fileStorageLocation) {
        this.fileStorageLocation = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String saveFile(
                           @Nonnull Long userId , @NonNull MultipartFile sourceFile) {
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


        return uploadFile(sourceFile);
    }

    @Override
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
    }

    @Override
    public Boolean deleteFile(String fileName) {
        try {
            Path filePath = loadFile(fileName);
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file " + fileName + ". Please try again!", e);
        }
    }

    @Override
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


}