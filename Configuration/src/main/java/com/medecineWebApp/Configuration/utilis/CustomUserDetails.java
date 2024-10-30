package com.medecineWebApp.Configuration.utilis;

/*
public class CustomUserDetails implements UserDetails {
    private final User user;
    public CustomUserDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<PermissionGrantedAuthority> authorities = new HashSet<>();

        // Convertir chaque permission de rôle en GrantedAuthority
        for (Roles role : user.getRoles()) {
            authorities.addAll(role.getPermissions().stream()
                    .map(permission -> new PermissionGrantedAuthority(permission.name()))
                    .collect(Collectors.toSet()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


}

 */
