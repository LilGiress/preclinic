package com.medecineWebApp.Configuration.models.user;


import com.medecineWebApp.Configuration.models.Auditable;
import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.models.Groupe;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.utilis.PermissionGrantedAuthority;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User extends Auditable implements UserDetails, Serializable, Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean enabled;
    private boolean accountLocked;

   /* @Enumerated(EnumType.STRING)
     private Role roles;*/

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
    private List<Departement> departments;

    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Groupe> groups;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<PermissionGrantedAuthority> authorities = new HashSet<>();

        // Convertir chaque permission de rôle en GrantedAuthority
        for (Roles role : roles) {
            authorities.addAll(role.getPermissions().stream()
                    .map(permission -> new PermissionGrantedAuthority(permission.name()))
                    .collect(Collectors.toSet()));
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return email;
    }

    public String getFullName(){
        return firstname + " " + lastname;
    }


}
