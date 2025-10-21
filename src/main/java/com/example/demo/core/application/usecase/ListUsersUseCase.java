package com.example.demo.core.application.usecase;

import java.util.List;


import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.in.ListUsersPort;
import com.example.demo.core.ports.out.UserRepositoryPort;
public class ListUsersUseCase implements ListUsersPort{
    private final UserRepositoryPort userRepository;

    public ListUsersUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
