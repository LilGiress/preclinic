package com.medecineWebApp.Configuration.models.user;


import com.medecineWebApp.Configuration.models.Auditable;
import com.medecineWebApp.Configuration.models.Departement;
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
    private String username;
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
    private List<Departement> departments;

    private Long employeeId;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // Convertir chaque permission de rôle en GrantedAuthority
        for (Roles role : roles) {
            for (Permission permission : role.getPermissions()) {
                if (permission.isCanRead()) {
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_READ"));
                }
                if (permission.isCanWrite()) {
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_WRITE"));
                }
                if (permission.isCanCreate()) {
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_CREATE"));
                }
                if (permission.isCanDelete()) {
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_DELETE"));
                }
                if (permission.isCanAssign()){
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_ASSIGN"));
                }
                if (permission.isCanImport()){
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_IMPORT"));
                }
                if(permission.isCanExport()){
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_EXPORT"));
                }
                if(permission.isCanApprove()){
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_APPROVE"));
                }
                if(permission.isCanActivate()){
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_ACTIVATE"));
                }
                if(permission.isCanValidate()){
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_VALIDATE"));
                }
                if(permission.isCanGenerateReport()){
                    authorities.add(new SimpleGrantedAuthority(permission.getRole().getName() + "_GENERATE"));
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
    public String getUsername() {
        return  firstname + " " + lastname;
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
