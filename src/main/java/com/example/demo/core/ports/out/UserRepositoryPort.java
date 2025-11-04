package com.example.demo.core.ports.out;

import java.util.Optional;

import com.example.demo.core.domain.models.User;

public interface UserRepositoryPort extends RepositoryPort<User, Long> {
    Optional<User> findByEmail(String email);

    void update(User user);
}
