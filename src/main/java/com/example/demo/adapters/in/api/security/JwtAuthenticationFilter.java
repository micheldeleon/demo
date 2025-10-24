package com.example.demo.adapters.in.api.security;

import javax.crypto.SecretKey;

import java.util.Collection;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final String PREFIX_TOKEN = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Collection<? extends GrantedAuthority> roles = user.getAuthorities();
        String username = user.getUsername();
        String token = Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .expiration(timer())
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(SECRET_KEY)
                .compact();
        response.addHeader("Authorization", "Bearer " + token);
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", "Authentication successful");
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private Date timer() {
        long oneDayInMs = 24 * 60 * 60 * 1000L; // 24h * 60min * 60s * 1000ms
        Date date = new Date(System.currentTimeMillis() + oneDayInMs);
        return date;
    }

}
