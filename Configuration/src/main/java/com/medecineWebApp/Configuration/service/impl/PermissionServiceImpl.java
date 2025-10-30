package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.PermissionDTO;
import com.medecineWebApp.Configuration.mapper.ActionPermissionMapper;
import com.medecineWebApp.Configuration.mapper.PermissionMapper;
import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.repository.permission.ActionPermissionRepository;
import com.medecineWebApp.Configuration.repository.permission.PermissionRepository;
import com.medecineWebApp.Configuration.service.PermissionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



@Service
public class PermissionServiceImpl implements PermissionService {
    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    private final ActionPermissionRepository actionPermissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper,  ActionPermissionRepository actionPermissionRepository) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
        this.actionPermissionRepository = actionPermissionRepository;

    }

    @Override
    public PermissionDTO createPermission(Permission permission) {
        Permission permission1= new Permission();
        permission1.setDescription(permission.getDescription());
        permission1.setLabel(permission.getLabel());
        permission1.setRole(permission.getRole());

        Permission permission2 = permissionRepository.save(permission1);
        for (ActionPermission actionPermission : permission.getActions()) {
            //log.warn("-------------------actionPermission----------",actionPermission.toString());
            ActionPermission actionPermission1 = new ActionPermission();
            actionPermission1.setLabel(actionPermission.getLabel());
            actionPermission1.setSelected(actionPermission.isSelected());
            actionPermission1.setPermissions(List.of(permission2));
            permission.addAction(actionPermission1);
            actionPermissionRepository.save(actionPermission1);

        }

        return permissionMapper.permissionToPermissionDTO(permission2);
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
