package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.config.CityLoaderService;
import com.medecineWebApp.Configuration.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {
    @Autowired
    private CityLoaderService cityLoaderService;

    @GetMapping
    public List<Region> getRegions() {
        return cityLoaderService.getRegions();
    }
}
