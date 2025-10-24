package com.example.demo.core.application.usecase;

import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.in.LoginUserPort;
import com.example.demo.core.ports.out.HasherPort;
import com.example.demo.core.ports.out.UserRepositoryPort;

public class LoginUserUseCase implements LoginUserPort {
    private final UserRepositoryPort userRepository;
    private final HasherPort hasherPort;

    public LoginUserUseCase(UserRepositoryPort userRepository, HasherPort hasherPort) {
        this.userRepository = userRepository;
        this.hasherPort = hasherPort;
    }
    @Override
    public void loginUser(String email, String password) {
        try{
            User user = userRepository.findByEmail(email);
            if(user == null || !hasherPort.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("Invalid email or password");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

}
