package com.example.demo.core.application.usecase;

import java.util.Date;
import java.util.List;

import com.example.demo.adapters.in.api.dto.ParticipantRequest;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentStatus;
import com.example.demo.core.ports.in.RegisterTeamToTournamentPort;
import com.example.demo.core.ports.out.TeamRegistrationPort;
import com.example.demo.core.ports.out.TournamentRepositoryPort;

public class RegisterTeamToTournamentUseCase implements RegisterTeamToTournamentPort {

    private final TournamentRepositoryPort tournamentRepositoryPort;
    private final TeamRegistrationPort teamRegistrationPort;

    public RegisterTeamToTournamentUseCase(TournamentRepositoryPort tournamentRepositoryPort,
            TeamRegistrationPort teamRegistrationPort) {
        this.tournamentRepositoryPort = tournamentRepositoryPort;
        this.teamRegistrationPort = teamRegistrationPort;
    }

    @Override
    public void registerTeam(Long tournamentId, Long userId, String teamName, List<ParticipantRequest> participants) {
        if (tournamentId == null || userId == null) {
            throw new IllegalArgumentException("tournamentId y userId son requeridos");
        }
        if (participants == null || participants.isEmpty()) {
            throw new IllegalArgumentException("Se requiere al menos un participante");
        }

        Tournament tournament = tournamentRepositoryPort.findById(tournamentId);
        if (tournament == null) {
            throw new IllegalArgumentException("Torneo no encontrado");
        }

        if (tournament.getStatus() != TournamentStatus.ABIERTO) {
            throw new IllegalStateException("El torneo no está abierto para inscripciones");
        }

        Date now = new Date();
        if (tournament.getRegistrationDeadline() != null && tournament.getRegistrationDeadline().before(now)) {
            throw new IllegalStateException("El torneo ya cerró inscripciones");
        }

        Long disciplineId = tournament.getDiscipline() != null ? tournament.getDiscipline().getId() : null;
        teamRegistrationPort.registerTeam(tournamentId, userId, teamName, disciplineId, participants);
    }
}
