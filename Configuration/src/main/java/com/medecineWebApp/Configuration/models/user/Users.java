package com.medecineWebApp.Configuration.models.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medecineWebApp.Configuration.models.Auditable;
import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.models.role.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class Users extends Auditable implements UserDetails, Serializable, Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String username = firstname + " " + lastname;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean enabled;
    private boolean accountLocked;
    private String photoUrl; // Chemin ou URL de l'image


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_department",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @JsonIgnore
    private List<Departement> departments;

    private Long employeeId;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // Convertir chaque permission de rôle en GrantedAuthority
        for (Roles role : roles) {
            // Nom du rôle (ex: ADMIN, MANAGER, etc.)
            String roleName = role.getName();
            for (Permission permission : role.getPermissions()) {
                // Nom du module (ex: TR_TRAINING, TR_PARTICIPANT, etc.)
                String module = permission.getLabel();

                for (ActionPermission action : permission.getActions()) {
                    if (action.isSelected()) {
                        // Exemple d'autorité : "ADMIN_TR_TRAINING_CAN_READ"
                        authorities.add(
                                new SimpleGrantedAuthority(roleName + "_" + module + "_" + action.getLabel())
                        );
                    }
                }
            }
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return getFullName();
    }

    public String getFullName(){
        return firstname + " " + lastname;
    }


}
