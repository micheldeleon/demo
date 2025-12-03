package com.example.demo.core.ports.in;

import com.example.demo.core.domain.models.User;

public interface GetUserByIdPort {
    User getUserById(Long id);
} 