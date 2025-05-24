package com.medecineWebApp.Asset.Management.feignClient;

import com.medecineWebApp.Asset.Management.dto.externe.RolesDTO;
import com.medecineWebApp.Asset.Management.dto.externe.UsersDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "api-gateway")
public interface UserFeignClient {
    @GetMapping("/api/Configuration/user/user-roles")
    List<UsersDTO> getUsersByRoles(@RequestParam List<String> roles);

    @GetMapping("/api/Configuration/roles")
    List<RolesDTO> getAllRoles();
}
