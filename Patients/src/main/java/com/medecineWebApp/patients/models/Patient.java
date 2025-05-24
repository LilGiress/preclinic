package com.medecineWebApp.patients.models;

import com.medecineWebApp.patients.enums.Gender;
import com.medecineWebApp.patients.enums.PatientStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Patient extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthDate;
    private Long addressId;
    private Long cityId;
    private String state;
    private String zip;
    private Long countryId;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;
    private String ImageUrl;
    private Long roleId;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Treatment> treatments;
    private Long appointmentId;
    private Long doctorId;
    private Long reviewId;
    private Long medicalRecordId;

    // Champ pour stocker les IDs des factures (optionnel)
//    @ElementCollection
//    @CollectionTable(name = "patient_invoices", joinColumns = @JoinColumn(name = "patient_id"))
//    @Column(name = "invoice_id")
    private Long invoiceIds;






}
