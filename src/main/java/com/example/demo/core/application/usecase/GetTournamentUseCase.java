package com.example.demo.core.application.usecase;

import java.util.List;

import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.ports.in.GetTournamentPort;
import com.example.demo.core.ports.out.TournamentRepositoryPort;

public class GetTournamentUseCase implements GetTournamentPort {

    private final TournamentRepositoryPort tournamentRepositoryPort;

    public GetTournamentUseCase(TournamentRepositoryPort tournamentRepositoryPort) {
        this.tournamentRepositoryPort = tournamentRepositoryPort;
    }

    @Override
    public List<Tournament> getSubscribedTournaments(Long userId) {
        List<Tournament> tournaments = tournamentRepositoryPort.findAll();
        if (tournaments == null) {
            throw new IllegalArgumentException("No hay torneos");
        }
        return tournaments
                .stream()
                .filter(t -> t.isParticipant(userId))
                .toList();
    }
}
