package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.CityDTO;
import com.medecineWebApp.Configuration.mapper.CityMapper;
import com.medecineWebApp.Configuration.models.City;
import com.medecineWebApp.Configuration.repository.CityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityController(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @GetMapping("all")
    public List<CityDTO> getAllCities() {
        List<City> city = cityRepository.findAll();
        return city.stream().map(cityMapper::cityToCityDTO).collect(Collectors.toList());
    }

    @GetMapping("/region/{regionId}")
    public List<CityDTO> getCitiesByRegion(@PathVariable Long regionId) {
        List<City> city = cityRepository.findByRegionId(regionId);
        return city.stream().map(cityMapper::cityToCityDTO).collect(Collectors.toList());
    }

    @PostMapping
    public CityDTO createCity(@RequestBody City city) {
        return cityMapper.cityToCityDTO(cityRepository.save(city));
    }
}
