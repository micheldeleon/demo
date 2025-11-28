package com.example.demo.core.application.usecase;

import com.example.demo.core.domain.models.User;
import com.example.demo.core.domain.services.ValidateUserService;
import com.example.demo.core.ports.in.GetUserPort;
import com.example.demo.core.ports.out.UserRepositoryPort;

public class GetUserUseCase implements GetUserPort {

    private final UserRepositoryPort repo;

    public GetUserUseCase(UserRepositoryPort userRepository) {
        this.repo = userRepository;
    }

    @Override
    public User getUserByIdAndEmail(Long id, String email) {
        if (id == 0 || id == null)
            throw new RuntimeException("Id invalido");
        ValidateUserService.validateEmailRequired(email);
        ValidateUserService.validateEmailFormat(email);
        User user = repo.findById(id);
        if (!user.getEmail().equals(email)) {
            throw new RuntimeException("No se encontro un usuario con ese mail e id");
        }
        return user;
    }

}
