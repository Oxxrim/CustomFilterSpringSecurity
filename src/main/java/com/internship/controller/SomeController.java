package com.internship.controller;

import com.internship.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class SomeController {

    @GetMapping("/test")
    public String username(@AuthenticationPrincipal User user) {

        return user.getUsername() + user.getAuthorities();
    }
}
