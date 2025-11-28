package com.example.demo.core.application.usecase;


import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.ports.in.GetTournamentByIdPort;

import com.example.demo.core.ports.out.TournamentRepositoryPort;

public class GetTournamentById implements GetTournamentByIdPort{
    private final TournamentRepositoryPort tournamentRepository;

    public GetTournamentById(TournamentRepositoryPort tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    
}
