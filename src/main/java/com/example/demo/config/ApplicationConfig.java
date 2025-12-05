package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.adapters.out.persistence.jpa.mappers.UserMapper;
import com.example.demo.core.application.usecase.CreateTournamentUseCase;
import com.example.demo.core.application.usecase.GetAllTournamentsUseCase;
import com.example.demo.core.application.usecase.GetTournamentById;
import com.example.demo.core.application.usecase.GetTournamentUseCase;
import com.example.demo.core.application.usecase.GetUserUseCase;
import com.example.demo.core.application.usecase.ListDisciplinesUseCase;
import com.example.demo.core.application.usecase.ListFormatsByDisciplineUseCase;
import com.example.demo.core.application.usecase.ListPublicTournamentsUseCase;
import com.example.demo.core.application.usecase.ListTournamentsByStatusUseCase;
import com.example.demo.core.application.usecase.ListUsersUseCase;
import com.example.demo.core.application.usecase.GenerateEliminationFixtureUseCase;
import com.example.demo.core.application.usecase.RegisterTeamToTournamentUseCase;
import com.example.demo.core.application.usecase.RegisterToTournamentUseCase;
import com.example.demo.core.application.usecase.RegisterUserUseCase;
import com.example.demo.core.application.usecase.ReportMatchResultUseCase;
import com.example.demo.core.application.usecase.UpdateUserUseCase;
import com.example.demo.core.ports.in.CreateTournamentPort;
import com.example.demo.core.ports.in.GetAllTournamentsPort;
import com.example.demo.core.ports.in.GenerateEliminationFixturePort;
import com.example.demo.core.ports.in.GetFixturePort;
import com.example.demo.core.ports.in.GetTournamentPort;
import com.example.demo.core.ports.in.GetUserPort;
import com.example.demo.core.ports.in.ListDisciplinesPort;
import com.example.demo.core.ports.in.ListFormatsByDisciplinePort;
import com.example.demo.core.ports.in.ListPublicTournamentsPort;
import com.example.demo.core.ports.in.ListTournamentsByStatusPort;
import com.example.demo.core.ports.in.ListUsersPort;
import com.example.demo.core.ports.in.RegisterTeamToTournamentPort;
import com.example.demo.core.ports.in.RegisterToTournamentPort;
import com.example.demo.core.ports.in.RegisterUserPort;
import com.example.demo.core.ports.in.ReportMatchResultPort;
import com.example.demo.core.ports.in.UpdateProfilePort;
import com.example.demo.core.ports.out.DisciplineRepositoryPort;
import com.example.demo.core.ports.out.FindTournamentsByStatusPort;
import com.example.demo.core.ports.out.FindTournamentsPort;
import com.example.demo.core.ports.out.FormatRepositoryPort;
import com.example.demo.core.ports.out.FixturePersistencePort;
import com.example.demo.core.ports.out.TeamRegistrationPort;
import com.example.demo.core.ports.out.TournamentRegistrationPort;
import com.example.demo.core.ports.out.TournamentRepositoryPort;
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
    public CreateTournamentPort CreateTournamentPort(TournamentRepositoryPort tournamentRepositoryPort) {
        return new CreateTournamentUseCase(tournamentRepositoryPort);
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

    @Bean
    public ListDisciplinesPort ListDisciplinesPort(DisciplineRepositoryPort disciplineRepositoryPort) {
        return new ListDisciplinesUseCase(disciplineRepositoryPort);
    }

    @Bean
    public ListFormatsByDisciplinePort ListFormatsByDisciplinePort(FormatRepositoryPort formatRepositoryPort) {
        return new ListFormatsByDisciplineUseCase(formatRepositoryPort);
    }

    @Bean
    public GetTournamentPort GetTournamentPort(TournamentRepositoryPort repo) {
        return new GetTournamentUseCase(repo);
    }

    @Bean
    public GetAllTournamentsPort GetAllTournamentsPort(TournamentRepositoryPort repo) {
        return new GetAllTournamentsUseCase(repo);
    }

    @Bean
    public GetTournamentById GetTournamentById(TournamentRepositoryPort repo) {
        return new GetTournamentById(repo);
    }

    @Bean
    public RegisterToTournamentPort RegisterToTournamentPort(TournamentRepositoryPort tournamentRepositoryPort,
            TournamentRegistrationPort tournamentRegistrationPort) {
        return new RegisterToTournamentUseCase(tournamentRepositoryPort, tournamentRegistrationPort);
    }

    @Bean
    public RegisterTeamToTournamentPort RegisterTeamToTournamentPort(TournamentRepositoryPort tournamentRepositoryPort,
            TeamRegistrationPort teamRegistrationPort) {
        return new RegisterTeamToTournamentUseCase(tournamentRepositoryPort, teamRegistrationPort);
    }

    @Bean
    public GenerateEliminationFixtureUseCase generateEliminationFixtureUseCase(
            TournamentRepositoryPort tournamentRepositoryPort,
            FixturePersistencePort fixturePersistencePort) {
        return new GenerateEliminationFixtureUseCase(tournamentRepositoryPort, fixturePersistencePort);
    }

    @Bean
    public GenerateEliminationFixturePort GenerateEliminationFixturePort(
            GenerateEliminationFixtureUseCase useCase) {
        return useCase;
    }

    @Bean
    public GetFixturePort GetFixturePort(GenerateEliminationFixtureUseCase useCase) {
        return useCase;
    }

    @Bean
    public ReportMatchResultPort ReportMatchResultPort(FixturePersistencePort fixturePersistencePort,
            TournamentRepositoryPort tournamentRepositoryPort) {
        return new ReportMatchResultUseCase(fixturePersistencePort, tournamentRepositoryPort);
    }
}
