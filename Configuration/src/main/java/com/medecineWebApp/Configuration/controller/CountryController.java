package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.CountryDTO;
import com.medecineWebApp.Configuration.mapper.CountryMapper;
import com.medecineWebApp.Configuration.models.Country;
import com.medecineWebApp.Configuration.repository.CountryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryController(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }
    @GetMapping
    public ResponseEntity <List<CountryDTO>> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return ResponseEntity.ok(countries.stream().map(
                countryMapper::countryToCountryDTO
        ).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity <CountryDTO> getCountryById(@PathVariable Long id) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new RuntimeException("Country not found with id: " + id));
        return ResponseEntity.ok(countryMapper.countryToCountryDTO(country));

    }

    @PostMapping
    public ResponseEntity <CountryDTO> createCountry(@RequestBody Country country) {
        return ResponseEntity.ok(countryMapper.countryToCountryDTO(countryRepository.save(country))) ;
    }
}
