package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.PermissionDTO;
import com.medecineWebApp.Configuration.mapper.PermissionMapper;
import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.repository.permission.PermissionRepository;
import com.medecineWebApp.Configuration.service.PermissionService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;


    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;

    }

    @Override
    public PermissionDTO createPermission(Permission permission) {
        permission.setCreatedDate(LocalDateTime.now());
        return permissionMapper.permissionToPermissionDTO(permissionRepository.save(permission));
    }

    @Override
    public PermissionDTO updatePermission(String label, List<ActionPermission> actionDTOs) {
        Permission permission = permissionRepository.findByLabel(label)
                .orElseThrow(() -> new RuntimeException("Permission non trouvée pour label : " + label));

        // mise à jour des actions
        permission.getActions().forEach(action -> {
            actionDTOs.stream()
                    .filter(dto -> dto.getLabel().equals(action.getLabel()))
                    .findFirst()
                    .ifPresent(dto -> action.setSelected(dto.isSelected()));
        });
        return permissionMapper.permissionToPermissionDTO(permissionRepository.save(permission));

    }

    @Override
    public List<PermissionDTO> listPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(permissionMapper::permissionToPermissionDTO)
                .toList();
    }


    @Override
    public List<ActionPermission> getActionsByLabel(String label) {
        return permissionRepository.findByLabel(label)
                .map(Permission::getActions)
                .orElseThrow(() -> new RuntimeException("Permission non trouvée pour le label : " + label));

    }

    @Override
    public PermissionDTO getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission non trouvée avec id " + id));
        return permissionMapper.permissionToPermissionDTO(permissionRepository.save(permission));
    }


}
