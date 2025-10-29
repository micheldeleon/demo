package com.example.demo.adapters.in.api.security;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

import java.util.Collection;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private static final String PREFIX_TOKEN = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "Authorization";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        Map<String, String> credentials = new HashMap<>();
        String username = null;
        String password = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), HashMap.class);
            username = credentials.get("username");
            password = credentials.get("password");
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username or password missing");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult
                .getPrincipal();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> roles = user.getAuthorities();
        Claims claims = Jwts.claims()
                .add("authorities", roles)
                .build();

        String token = Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(timer())
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(jwtUtil.getSecretKey())
                .compact();
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", String.format("User %s logged in successfully", username));
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private Date timer() {
        long oneDayInMs = 24 * 60 * 60 * 1000L; // 24h * 60min * 60s * 1000ms
        Date date = new Date(System.currentTimeMillis() + oneDayInMs);
        return date;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autenticacion username o password incorrectos!");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }

}
