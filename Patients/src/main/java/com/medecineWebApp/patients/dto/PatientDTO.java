package com.medecineWebApp.patients.dto;

import com.medecineWebApp.patients.enums.Gender;
import com.medecineWebApp.patients.enums.PatientStatus;
import com.medecineWebApp.patients.models.Treatment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO extends AuditableDTO{
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "patient_email",nullable = false)
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

    private List<TreatmentDTO> treatments;
    private Long appointmentId;
    private Long doctorId;
    private Long reviewId;
    private Long medicalRecordId;

    // Champ pour stocker les IDs des factures (optionnel)
    /*@ElementCollection
    @CollectionTable(name = "patient_invoices", joinColumns = @JoinColumn(name = "patient_id"))
    @Column(name = "invoice_id")*/
    private Long invoiceIds ;

}
