package com.example.demo.core.application.usecase;

import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.in.GetUserByIdPort;
import com.example.demo.core.ports.out.UserRepositoryPort;

public class GetUserByIdUseCase implements GetUserByIdPort {

    private final UserRepositoryPort repo;

    public GetUserByIdUseCase(UserRepositoryPort userRepository) {
        this.repo = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        if (id == 0 || id == null)
            throw new RuntimeException("Id invalido");
        User user = repo.findById(id);
        return user;
    }

    

}
