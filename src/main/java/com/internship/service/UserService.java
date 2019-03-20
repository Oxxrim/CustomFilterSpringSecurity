package com.internship.service;

import com.internship.domain.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final static ArrayList<User> db = new ArrayList<>(Arrays.asList(
            new User("admin", "password", Collections.singleton(Role.ADMIN)),
            new User("user", "password", Collections.singleton(Role.USER))));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return db.stream().filter(user -> user.getUsername().equals(username))
                .findFirst()
                .get();
    }
}
