package ru.edu.helpdesk.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.edu.helpdesk.entity.User;

import java.util.Collection;
import java.util.Collections;

public class HelpdeskUserPrincipal implements UserDetails {

    private final User user;

    public User getUser() {
        return user;
    }

    public HelpdeskUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}