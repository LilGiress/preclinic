package com.medecineWebApp.Configuration.models.setting;

import com.medecineWebApp.Configuration.enums.AssetCategory;
import com.medecineWebApp.Configuration.enums.AssetStatus;
import com.medecineWebApp.Configuration.models.Auditable;
import com.medecineWebApp.Configuration.models.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
@Entity
@Table(name = "config_assets")
public class Asset extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private AssetCategory category;

    private LocalDate acquisitionDate;

    private Double value;

    private String description;

    @Enumerated(EnumType.STRING)
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

    public Asset() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetCategory getCategory() {
        return category;
    }

    public void setCategory(AssetCategory category) {
        this.category = category;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseFrom() {
        return purchaseFrom;
    }

    public void setPurchaseFrom(String purchaseFrom) {
        this.purchaseFrom = purchaseFrom;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Number getWarranty() {
        return Warranty;
    }

    public void setWarranty(Number warranty) {
        Warranty = warranty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Asset(String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate, String name, AssetCategory category, LocalDate acquisitionDate, Double value, String description, AssetStatus status, LocalDate purchaseDate, String purchaseFrom, String model, String serialNumber, String supplier, String condition, Number warranty, User user) {
        super(createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.name = name;
        this.category = category;
        this.acquisitionDate = acquisitionDate;
        this.value = value;
        this.description = description;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.purchaseFrom = purchaseFrom;
        this.Model = model;
        this.serialNumber = serialNumber;
        this.supplier = supplier;
        this.condition = condition;
       this. Warranty = warranty;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", acquisitionDate=" + acquisitionDate +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", purchaseDate=" + purchaseDate +
                ", purchaseFrom='" + purchaseFrom + '\'' +
                ", Model='" + Model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", supplier='" + supplier + '\'' +
                ", condition='" + condition + '\'' +
                ", Warranty=" + Warranty +
                ", user=" + user +
                '}';
    }
}
