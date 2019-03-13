package com.internship.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class CustomFilter extends GenericFilterBean {
    public static final String HEADER_TOKEN = "token";
    public static final String HEADER_TOKEN_ROLE = "token-role";
    public static final String DEFAULT_ROLE = "ROLE_USER";
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpReq = (HttpServletRequest) request;

            String token = httpReq.getHeader(HEADER_TOKEN);
            if(token != null) {
                String username = token.trim();
                if(!username.isEmpty()) {
                    String role = Optional.ofNullable(httpReq.getHeader(HEADER_TOKEN_ROLE)).orElse(DEFAULT_ROLE);
                    Token auth = new Token(username, Collections.singletonList(new SimpleGrantedAuthority(role)));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }



        chain.doFilter(request, response);
    }
}
