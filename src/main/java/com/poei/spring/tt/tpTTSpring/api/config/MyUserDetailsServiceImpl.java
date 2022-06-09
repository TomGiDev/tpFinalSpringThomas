package com.poei.spring.tt.tpTTSpring.api.config;

import com.poei.spring.tt.tpTTSpring.exception.UnknownRessourceException;
import com.poei.spring.tt.tpTTSpring.model.User;
import com.poei.spring.tt.tpTTSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User existingUser = this.userService.getByUsername(username);
            return new MyUserDetails(existingUser);
        } catch (UnknownRessourceException ure) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
    }
}
