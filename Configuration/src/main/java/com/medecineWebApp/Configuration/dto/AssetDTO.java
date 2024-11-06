package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.enums.AssetCategory;
import com.medecineWebApp.Configuration.enums.AssetStatus;
import com.medecineWebApp.Configuration.models.user.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssetDTO {
    private Long id;

    private String name;

    private AssetCategory category;

    private LocalDate acquisitionDate;

    private Double value;

    private String description;

    private AssetStatus status;

    private LocalDate purchaseDate;

    private String purchaseFrom;

    private String Model;

    private String serialNumber;

    private String supplier;

    private String condition;

    private Number Warranty;

    @Transient
    private User user;
}
