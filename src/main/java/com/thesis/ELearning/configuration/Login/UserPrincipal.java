package com.thesis.ELearning.configuration.Login;

import com.thesis.ELearning.entity.Authorities;
import com.thesis.ELearning.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private  final User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     List<Authorities> role = new ArrayList<>();
     role.add(new Authorities(user.getUserRole()));
        return role;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNotExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isAccountNotExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
