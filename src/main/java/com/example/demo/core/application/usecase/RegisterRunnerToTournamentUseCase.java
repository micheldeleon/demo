package com.example.demo.core.application.usecase;

import java.util.Date;
import java.util.List;

import com.example.demo.adapters.in.api.dto.ParticipantRequest;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentStatus;
import com.example.demo.core.domain.models.Formats.RaceFormat;
import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.in.RegisterRunnerToTournamentPort;
import com.example.demo.core.ports.out.TeamRegistrationPort;
import com.example.demo.core.ports.out.TournamentRepositoryPort;
import com.example.demo.core.ports.out.UserRepositoryPort;
import com.example.demo.adapters.in.api.dto.RunnerRegistrationRequest;

public class RegisterRunnerToTournamentUseCase implements RegisterRunnerToTournamentPort {

    private final TournamentRepositoryPort tournamentRepositoryPort;
    private final TeamRegistrationPort teamRegistrationPort;
    private final UserRepositoryPort userRepositoryPort;

    public RegisterRunnerToTournamentUseCase(TournamentRepositoryPort tournamentRepositoryPort,
            TeamRegistrationPort teamRegistrationPort,
            UserRepositoryPort userRepositoryPort) {
        this.tournamentRepositoryPort = tournamentRepositoryPort;
        this.teamRegistrationPort = teamRegistrationPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void register(Long tournamentId, String userEmail, RunnerRegistrationRequest request) {
        if (tournamentId == null || userEmail == null || userEmail.isBlank()) {
            throw new IllegalArgumentException("tournamentId y userEmail son requeridos");
        }

        Tournament tournament = tournamentRepositoryPort.findById(tournamentId);
        if (tournament == null) {
            throw new IllegalArgumentException("Torneo no encontrado");
        }
        if (!(tournament.getFormat() instanceof RaceFormat)) {
            throw new IllegalStateException("El formato del torneo no es carrera");
        }
        if (tournament.getStatus() != TournamentStatus.ABIERTO) {
            throw new IllegalStateException("El torneo no esta abierto para inscripciones");
        }
        Date now = new Date();
        if (tournament.getRegistrationDeadline() != null && tournament.getRegistrationDeadline().before(now)) {
            throw new IllegalStateException("El torneo ya cerro inscripciones");
        }

        User user = userRepositoryPort.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String fullName = resolveFullName(user, request);
        String nationalId = resolveNationalId(user, request);

        String teamName = fullName;
        ParticipantRequest runner = new ParticipantRequest(fullName, nationalId);
        Long disciplineId = tournament.getDiscipline() != null ? tournament.getDiscipline().getId() : null;

        teamRegistrationPort.registerTeam(tournamentId, user.getId(), teamName, disciplineId, List.of(runner));
    }

    private String resolveFullName(User user, RunnerRegistrationRequest request) {
        if (request != null && request.fullName() != null && !request.fullName().isBlank()) {
            return request.fullName().trim();
        }
        String fullName = (user.getName() != null ? user.getName() : "").trim();
        if (user.getLastName() != null && !user.getLastName().isBlank()) {
            fullName = (fullName + " " + user.getLastName()).trim();
        }
        if (fullName.isBlank()) {
            throw new IllegalArgumentException("El usuario debe tener nombre completo cargado");
        }
        return fullName;
    }

    private String resolveNationalId(User user, RunnerRegistrationRequest request) {
        if (user.getNationalId() != null && !user.getNationalId().isBlank()) {
            return user.getNationalId();
        }
        if (request != null && request.nationalId() != null && !request.nationalId().isBlank()) {
            return request.nationalId();
        }
        throw new IllegalArgumentException("Debe proporcionar cedula/nationalId");
    }
}
