package com.medecineWebApp.Employees.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "asset-service")
public interface AssetClient {
//    @GetMapping("/api/assets/doctor/{doctorId}")
//    List<AssetsDTO> getAssetsByDoctor(@PathVariable Long doctorId);
}
