package com.medecineWebApp.Configuration.service;


import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {
     String saveFile(Long userId, MultipartFile file) ;
     String uploadFile( MultipartFile file) ;
     Boolean deleteFile(String fileName);
     String getFileExtension(String fileName);
    Path loadFile(String fileName);

}
