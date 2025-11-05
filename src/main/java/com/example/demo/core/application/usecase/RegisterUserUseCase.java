package com.example.demo.core.application.usecase;

import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.in.RegisterUserPort;
import com.example.demo.core.ports.out.UserRepositoryPort;

public class RegisterUserUseCase implements RegisterUserPort {
    private final UserRepositoryPort userRepository;
    public RegisterUserUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        try{
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
    }



}
