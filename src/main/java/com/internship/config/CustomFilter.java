package com.internship.config;

import com.internship.domain.User;
import com.internship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CustomFilter extends GenericFilterBean {

    @Autowired
    private UserService service;

    private User user;

    /*@PostConstruct
    public void init() {
        user = (User) service.loadUserByUsername("admin");
    }*/


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            user = (User) service.loadUserByUsername(httpReq.getHeader("username"));
            if (user != null && user.getPassword().equals(httpReq.getHeader("password"))){
                SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(user));
            }
        }

        chain.doFilter(request, response);
    }
}
