package com.thesis.ELearning.service;

import com.thesis.ELearning.configuration.Login.UserPrincipal;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private User user;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userService.findByEmail(email);

        if (user == null ) throw new UsernameNotFoundException(email);
        this.user = user;
        return new UserPrincipal(user);
    }

    public User getUser() {
        return user;
    }
}
