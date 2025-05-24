package com.medecineWebApp.Configuration.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface ProfilePhotoService {
    String storeProfilePhoto(MultipartFile file, Long userId, String role)throws IOException;
    Path getProfilePhotoPath(Long userId, String role);
}
