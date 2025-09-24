package com.example.demo.security;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.EndpointPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class CustomUserDetails implements UserDetails {
    private final User user;
    private final Set<GrantedAuthority> authorities;
    private final Set<EndpointPermission> endpointPermissions;

    public CustomUserDetails(User user) {
        this.user = user;
        this.endpointPermissions = new HashSet<>();
        Set<GrantedAuthority> auths = new HashSet<>();
        for (Role role : user.getRoles()) {
            auths.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            if (role.getPermissions() != null) {
                for (EndpointPermission perm : role.getPermissions()) {
                    auths.add(new SimpleGrantedAuthority(perm.getName()));
                    endpointPermissions.add(perm);
                }
            }
        }
        this.authorities = auths;
    }

    public Set<EndpointPermission> getEndpointPermissions() {
        return endpointPermissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override
    public String getPassword() { return user.getPassword(); }
    @Override
    public String getUsername() { return user.getUsername(); }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
    public User getUser() { return user; }
}