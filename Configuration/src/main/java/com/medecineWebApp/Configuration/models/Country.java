package com.medecineWebApp.Configuration.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("name") // Mapper correctement le JSON
    private String name;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference("country-region")
    private List<Region> regions;
}
