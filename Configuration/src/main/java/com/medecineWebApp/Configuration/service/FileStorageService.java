package com.medecineWebApp.Configuration.service;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

public interface FileStorageService {
    /// String saveFile(Long userId, MultipartFile file) ;
    /// String uploadFile( MultipartFile file) ;
    ///Boolean deleteFile(String fileName);
    /// String getFileExtension(String fileName);
   /// Path loadFile(String fileName);

   String storeFile(MultipartFile file, Long patientId, String category) throws IOException;
    Resource loadFileAsResource(String filePath) throws MalformedURLException, FileNotFoundException;
    String storeFileForPatient(MultipartFile file, Long patientId, String category) throws IOException;
    String storeFileForDoctor(MultipartFile file, Long doctorId, String category) throws IOException;
    String storeFileForManagement(MultipartFile file, String category) throws IOException;
    String createFolder(Long userId, String role, String folderName) throws IOException;
    List<String> listFolders(Long userId, String role) throws IOException;
    String deleteFolder(Long userId, String role, String folderName) throws IOException;

}
