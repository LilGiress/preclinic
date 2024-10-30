package com.medecineWebApp.Configuration.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "config_Holidays")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Holiday extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
}
