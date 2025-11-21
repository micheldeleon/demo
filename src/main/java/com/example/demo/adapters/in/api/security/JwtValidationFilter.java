package com.example.demo.adapters.in.api.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String PREFIX_TOKEN = "Bearer ";
    private final JwtUtil jwtUtil;

    public JwtValidationFilter(AuthenticationManager authenticationManager, JwtUtil jwtu) {
        super(authenticationManager);
        this.jwtUtil = jwtu;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);
        String header = request.getHeader(HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            return;
        }
        String token = header.replace(PREFIX_TOKEN, "");
        try {
            Jwts.parser()
                    .verifyWith(jwtUtil.getSecretKey())
                    .build()
                    .parseSignedClaims(token);

        } catch (Exception e) {

        }
    }

}
