package com.example.demo.core.application.usecase;

import java.util.Date;

import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentStatus;
import com.example.demo.core.ports.in.RegisterToTournamentPort;
import com.example.demo.core.ports.out.TournamentRegistrationPort;
import com.example.demo.core.ports.out.TournamentRepositoryPort;

public class RegisterToTournamentUseCase implements RegisterToTournamentPort {

    private final TournamentRepositoryPort tournamentRepositoryPort;
    private final TournamentRegistrationPort tournamentRegistrationPort;

    public RegisterToTournamentUseCase(TournamentRepositoryPort tournamentRepositoryPort,
            TournamentRegistrationPort tournamentRegistrationPort) {
        this.tournamentRepositoryPort = tournamentRepositoryPort;
        this.tournamentRegistrationPort = tournamentRegistrationPort;
    }

    @Override
    public void register(Long tournamentId, Long userId) {
        if (tournamentId == null || userId == null) {
            throw new IllegalArgumentException("tournamentId y userId son requeridos");
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

        if (tournamentRegistrationPort.exists(tournamentId, userId)) {
            throw new IllegalStateException("El usuario ya está inscrito en el torneo");
        }

        tournamentRegistrationPort.register(tournamentId, userId);
    }
}
