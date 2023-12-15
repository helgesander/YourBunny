package ru.yourbunny.yourbunny.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.yourbunny.yourbunny.models.User;

import java.util.Collection;

@AllArgsConstructor
public class SiteUserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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

    // Need to get data of user after authentication
    public User getUser() {
        return this.user;
    }
    
}
