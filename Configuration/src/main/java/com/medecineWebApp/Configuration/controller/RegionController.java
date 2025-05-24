package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.config.CityLoaderService;
import com.medecineWebApp.Configuration.dto.RegionDTO;
import com.medecineWebApp.Configuration.mapper.RegionMapper;
import com.medecineWebApp.Configuration.models.Region;
import com.medecineWebApp.Configuration.repository.RegionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    private final CityLoaderService cityLoaderService;
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public RegionController(CityLoaderService cityLoaderService, RegionRepository regionRepository, RegionMapper regionMapper) {
        this.cityLoaderService = cityLoaderService;
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    // Mettre à jour les régions en live
    @PostMapping("/update")
    public String updateRegions() {
        cityLoaderService.loadCitiesAndRegions();
        return "✅ Régions mises à jour avec succès !";
    }
    @PostMapping
    public ResponseEntity<RegionDTO> saveRegion(@RequestBody Region region) {
        return ResponseEntity.ok(regionMapper.regionToRegionDTO(regionRepository.save(region)));

    }

    // Récupérer toutes les régions
    @GetMapping
    public ResponseEntity<List<RegionDTO>> getAllRegions() {
        return ResponseEntity.ok(regionRepository.findAll().stream().map(
                regionMapper::regionToRegionDTO
        ).toList())

        ;
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<RegionDTO>> getRegionsByCountry(@PathVariable Long countryId) {
        List<Region> regions = regionRepository.findByCountryId(countryId);
        return  ResponseEntity.ok(regions
                .stream()
                .map(regionMapper::regionToRegionDTO)
                .collect(Collectors.toList()));
    }
}
