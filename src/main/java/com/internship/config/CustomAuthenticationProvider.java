package com.internship.config;

import com.internship.domain.Role;
import com.internship.domain.User;
import com.internship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = (User) service.loadUserByUsername(name);

        if (user != null && user.getUsername().equals(name) && user.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(user, password, Arrays.asList(Role.ADMIN, Role.USER));
        } else {
            throw new BadCredentialsException("Authentication failed");
        }


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
