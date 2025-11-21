package com.example.demo.adapters.in.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String PREFIX_TOKEN = "Bearer ";
    private static final String CLAIM_AUTHORITIES = "authorities";
    private static final String ERROR_BODY = "{\"message\":\"Token invalido o expirado\"}";

    private final JwtUtil jwtUtil;

    public JwtValidationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_AUTHORIZATION);
        if (!hasBearerToken(header)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(PREFIX_TOKEN.length()).trim();
        Claims claims = validateToken(token);
        if (claims == null) {
            handleInvalidToken(response);
            return;
        }

        String username = claims.getSubject();
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authentication
                    = buildAuthentication(username, claims, request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private boolean hasBearerToken(String header) {
        return header != null && header.startsWith(PREFIX_TOKEN);
    }

    private Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(jwtUtil.getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException ex) {
            SecurityContextHolder.clearContext();
            return null;
        }
    }

    private UsernamePasswordAuthenticationToken buildAuthentication(String username, Claims claims,
            HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(username, null, extractAuthorities(claims));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }

    private void handleInvalidToken(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(ERROR_BODY);
    }

    @SuppressWarnings("unchecked")
    private List<GrantedAuthority> extractAuthorities(Claims claims) {
        Object raw = claims.get(CLAIM_AUTHORITIES);
        if (raw instanceof Collection<?> collection) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Object entry : collection) {
                authorities.add(mapAuthority(entry));
            }
            return authorities;
        }
        return Collections.emptyList();
    }

    private GrantedAuthority mapAuthority(Object entry) {
        if (entry instanceof Map<?, ?> map) {
            Object authority = map.get("authority");
            if (authority == null) {
                authority = map.get("role");
            }
            if (authority != null) {
                return new SimpleGrantedAuthority(authority.toString());
            }
        }
        return new SimpleGrantedAuthority(entry.toString());
    }
}
