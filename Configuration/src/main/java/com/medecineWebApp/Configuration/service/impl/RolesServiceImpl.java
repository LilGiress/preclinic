package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.RolesDTO;
import com.medecineWebApp.Configuration.exception.RolesNotFoundException;
import com.medecineWebApp.Configuration.mapper.RolesMapper;
import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.payload.request.ActionPermissionRequest;
import com.medecineWebApp.Configuration.payload.request.PermissionRequest;
import com.medecineWebApp.Configuration.payload.request.RolesRequest;
import com.medecineWebApp.Configuration.payload.request.UpdateRoleRequest;
import com.medecineWebApp.Configuration.repository.permission.ActionPermissionRepository;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import com.medecineWebApp.Configuration.service.RolesService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RolesServiceImpl implements RolesService {

    private final RoleRepository roleRepository;
    private final RolesMapper rolesMapper;
    private final PermissionServiceImpl permissionServiceImpl;
    private final ActionPermissionRepository actionPermissionRepository;

    public RolesServiceImpl(RoleRepository roleRepository, RolesMapper rolesMapper, PermissionServiceImpl permissionServiceImpl, ActionPermissionRepository actionPermissionRepository) {
        this.roleRepository = roleRepository;
        this.rolesMapper = rolesMapper;
        this.permissionServiceImpl = permissionServiceImpl;
        this.actionPermissionRepository = actionPermissionRepository;
    }

    @Transactional
    @Override
    public RolesDTO updateRole(Long roleId, UpdateRoleRequest request) {
        // Vérifier si le rôle existe
        Roles role =  roleRepository.findById(roleId)
                .orElseThrow(() -> new RolesNotFoundException("Role with ID " + roleId + " does not exist."));
        // 2. Mettre à jour les infos basiques du rôle
        role.setName(request.getName());

        // 3. Supprimer les anciennes permissions (grâce à orphanRemoval = true)
        role.getPermissions().clear();

        log.info("Updating role --------------------: " + request.getName());

        // 4. Ajouter les nouvelles permissions depuis la requête
        for (Permission permission : request.getPermissions()) {
            permission.setLabel(permission.getLabel());
            permission.setDescription(permission.getDescription());
            permission.setRole(role); // lien bidirectionnel

            // Ajouter les actions
            for (ActionPermission actionDTO : permission.getActions()) {
                ActionPermission action = new ActionPermission();
                action.setLabel(actionDTO.getLabel());
                action.setSelected(actionDTO.isSelected());
                action.setPermissions((List<Permission>) permission); // lien bidirectionnel
                permission.getActions().add(action);
            }
            role.getPermissions().add(permission);
        }

        // Sauvegarder et retourner le rôle mis à jour
        log.info("Updated list of permissions --------------------: " + request.getPermissions());
        return rolesMapper.rolesToRolesDTO(roleRepository.save(role));
    }


    @Override
    public Optional<RolesDTO> findByName(String role) {
        return roleRepository.findByName(role).map(rolesMapper::rolesToRolesDTO);
    }

    @Override
    public List<RolesDTO> findAllRoles() {
        return roleRepository.findAll().stream().map(rolesMapper::rolesToRolesDTO).toList();
    }

    @Override
    public void deleteRole(Long roleId) {
        // Vérifier si le rôle existe
        Roles role =  roleRepository.findById(roleId)
                .orElseThrow(() -> new RolesNotFoundException("Role not found"));
        // Supprimer le rôle
        roleRepository.delete(role);
       // log.info("Deleted role --------------------: " + role.getName());
    }

    @Override
    @Transactional
    public RolesDTO createRoleWithPermissions(RolesRequest request) {
        // Associer les permissions au rôle
        Roles role = new Roles();
        role.setName(request.getName().toUpperCase());
        role.setDescription(request.getDescription());


        for (PermissionRequest permission : request.getPermissions()) {
            Permission permission1 = new Permission();
            permission1.setLabel(permission.getLabel());
            permission1.setDescription(permission.getDescription());
            List<ActionPermission> actionList=new ArrayList<>();

            permission1.setRole(role);

            for (ActionPermissionRequest actionDTO : permission.getActions()) {
                // 🔍 Si les actions existent déjà, il vaut mieux les chercher via repo
                ActionPermission action = actionPermissionRepository.findByLabel((actionDTO.getLabel()))
                        .orElseGet(() -> {
                            ActionPermission newAction= new ActionPermission();
                            newAction.setLabel(actionDTO.getLabel());
                            newAction.setSelected(actionDTO.isSelected());
                            return actionPermissionRepository.save(newAction);
                        });
                actionList.add(action);



            }
            permission1.setActions(actionList);
            role.addPermission(permission1);
          // permissionServiceImpl.createPermission(permission1);

        }
        Roles roleSaved=roleRepository.save(role);
        return rolesMapper.rolesToRolesDTO(roleSaved);
    }



}
