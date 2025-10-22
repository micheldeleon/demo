package com.example.demo.core.ports.in;

import com.example.demo.core.domain.models.User;

public interface RegisterUserPort {
    void registerUser(User user);
}
