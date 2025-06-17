package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.PermissionDTO;
import com.medecineWebApp.Configuration.exception.PermissionNotFoundException;
import com.medecineWebApp.Configuration.mapper.PermissionMapper;
import com.medecineWebApp.Configuration.models.ModulePermission;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.repository.ModulePermissionRepository;
import com.medecineWebApp.Configuration.repository.permission.PermissionRepository;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import com.medecineWebApp.Configuration.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    private final ModulePermissionRepository permissionModuleRepository;
    private final UserRepository userRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper, ModulePermissionRepository permissionModuleRepository, UserRepository userRepository) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
        this.permissionModuleRepository = permissionModuleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PermissionDTO createPermission(List<Permission> permission) {
        if (permission != null ) {
            for (Permission permissionItem : permission) {
                // permissionRepository.save(permissionItem);
                return permissionMapper.permissionToPermissionDTO(permissionRepository.save(permissionItem));
            }
        }
        throw new PermissionNotFoundException("permission is null ");
    }

    @Override
    public PermissionDTO updatePermission(Long id,Permission permission) {
        Optional<Permission> permissionOptional = permissionRepository.findById(id);
        if (permissionOptional.isPresent()) {
            Permission permissionToUpdate = permissionOptional.get();
          //  permissionToUpdate.setDescription(permission.getDescription());
            return permissionMapper.permissionToPermissionDTO(permissionRepository.save(permissionToUpdate));
        }
         throw new PermissionNotFoundException("Permission not found");
    }

    @Override
    public Page<PermissionDTO> listPermissions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return permissionRepository.findAll(pageable).map(permissionMapper::permissionToPermissionDTO);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionRepository.deleteById(permissionId);

    }

    @Override
    public ModulePermission createModulePermission(String moduleName, List<Permission> permissions) {
        ModulePermission modulePermission = new ModulePermission();
        modulePermission.setModuleName(moduleName);
        modulePermission.setPermissions(permissions);
        return permissionModuleRepository.save(modulePermission);
    }

    @Override
    public List<ModulePermission> listModulePermissions() {
        return permissionModuleRepository.findAll();
    }

    @Override
    public List<Permission> listPermissions(List<Long> permissionsId) {
        return permissionRepository.findByIdIn(permissionsId);
    }





}
