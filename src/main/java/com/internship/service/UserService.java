package com.internship.service;

import com.internship.domain.Role;

import com.internship.domain.User;
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
            new User("admin", "password", 13, Collections.singletonList(Role.ADMIN),true, true, true, true),
            new User("user", "password", 12, Collections.singletonList(Role.USER), true, true, true, true)));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = db.stream().filter(usr -> usr.getUsername().equals(username))
                .findFirst()
                .get();

        return  user;
    }
}
