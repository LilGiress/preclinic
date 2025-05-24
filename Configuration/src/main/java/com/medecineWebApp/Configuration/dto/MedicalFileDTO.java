package com.medecineWebApp.Configuration.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalFileDTO extends AuditableDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private String filePath;
    private Long patientId;
    private LocalDateTime uploadedAt = LocalDateTime.now();
}
