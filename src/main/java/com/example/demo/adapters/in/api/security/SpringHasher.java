package com.example.demo.adapters.in.api.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.core.ports.out.HasherPort;

@Component
public class SpringHasher implements HasherPort {
    private final PasswordEncoder passwordEncoder;

    public SpringHasher(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String hash(String raw) {
        return passwordEncoder.encode(raw);
    }

    @Override
    public boolean matches(String raw, String hashed) {
        return passwordEncoder.matches(raw, hashed);
    }

}
