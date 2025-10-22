package com.example.demo.core.application.usecase;

import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.in.LoginUserPort;
import com.example.demo.core.ports.out.UserRepositoryPort;

public class LoginUserUseCase implements LoginUserPort {
    private final UserRepositoryPort userRepository;
    public LoginUserUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void loginUser(String email, String password) {
        try{
            User user = userRepository.findByEmail(email);
            if(user == null || !user.getPassword().equals(password)) {
                throw new IllegalArgumentException("Invalid email or password");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

}
