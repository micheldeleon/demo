package com.example.demo.core.ports.out;

import com.example.demo.core.domain.models.User;

public interface UserRepositoryPort extends RepositoryPort<User, Long> {
    User findByEmail(String email);
}
