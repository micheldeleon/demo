package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.adapters.out.persistence.jpa.mappers.UserMapper;
import com.example.demo.core.application.usecase.CreateTournamentUseCase;
import com.example.demo.core.application.usecase.GetUserUseCase;
import com.example.demo.core.application.usecase.ListUsersUseCase;
import com.example.demo.core.application.usecase.ListPublicTournamentsUseCase;
import com.example.demo.core.application.usecase.ListTournamentsByStatusUseCase;
import com.example.demo.core.application.usecase.RegisterUserUseCase;
import com.example.demo.core.application.usecase.UpdateUserUseCase;
import com.example.demo.core.ports.in.CreateTournamentPort;
import com.example.demo.core.ports.in.GetUserPort;
import com.example.demo.core.ports.in.ListPublicTournamentsPort;
import com.example.demo.core.ports.in.ListTournamentsByStatusPort;
import com.example.demo.core.ports.in.ListUsersPort;
import com.example.demo.core.ports.in.RegisterUserPort;
import com.example.demo.core.ports.in.UpdateProfilePort;
import com.example.demo.core.ports.out.FindTournamentsByStatusPort;
import com.example.demo.core.ports.out.FindTournamentsPort;
import com.example.demo.core.ports.out.SaveTournamentPort;
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

    @Bean
    public UpdateProfilePort UpdateProfilePort(UserRepositoryPort userRepositoryPort) {
        return new UpdateUserUseCase(userRepositoryPort);
    }

    @Bean
    public GetUserPort GetUserPort(UserRepositoryPort userRepositoryPort) {
        return new GetUserUseCase(userRepositoryPort);
    }
    @Bean
    public CreateTournamentPort CreateTournamentPort(SaveTournamentPort saveTournamentPort) {
        return new CreateTournamentUseCase(saveTournamentPort);
    }

    @Bean
    public ListPublicTournamentsPort listPublicTournamentsPort(FindTournamentsPort findTournamentsPort) {
        return new ListPublicTournamentsUseCase(findTournamentsPort);
    }

    @Bean
    public ListTournamentsByStatusPort listTournamentsByStatusPort(
            FindTournamentsByStatusPort findTournamentsByStatusPort) {
        return new ListTournamentsByStatusUseCase(findTournamentsByStatusPort);
    }
}
