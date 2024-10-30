package com.medecineWebApp.doctors.models;

import com.medecineWebApp.doctors.emuns.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Doctors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Doctor extends User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctorBirthday;
    private String imageUrl;
    private String biography;
    private String doctorPostalCode;
    private String doctorCountry;
    private boolean status;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;


    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime dateOfCreation;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;


}
