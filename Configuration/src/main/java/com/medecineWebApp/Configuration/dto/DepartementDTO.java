package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartementDTO extends AuditableDTO {
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    private List<ServicesDTO> services = new ArrayList<>();

    private Long userId;
    private Long leaveId;

}
