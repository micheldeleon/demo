package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.adapters.out.persistence.jpa.mappers.UserMapper;
import com.example.demo.core.application.usecase.ListUsersUseCase;
import com.example.demo.core.application.usecase.RegisterUserUseCase;
import com.example.demo.core.ports.in.ListUsersPort;
import com.example.demo.core.ports.in.RegisterUserPort;
import com.example.demo.core.ports.out.UserRepositoryPort;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public ListUsersPort listUsersPort(UserRepositoryPort userRepositoryPort) {
        return new ListUsersUseCase(userRepositoryPort);
    }

    @Bean
    public RegisterUserPort registerUserPort(UserRepositoryPort userRepositoryPort) {
        return new RegisterUserUseCase(userRepositoryPort);
    }

}
