package com.internship.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class SomeController {

    @GetMapping("test")
    public String username(Principal principal) {

        return principal.getName();
    }
}
