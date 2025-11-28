package com.example.demo.core.application.usecase;


import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.ports.in.CreateTournamentPort;
import com.example.demo.core.ports.out.TournamentRepositoryPort;

public class CreateTournamentUseCase implements CreateTournamentPort {

    private final TournamentRepositoryPort savePort;

    public CreateTournamentUseCase(TournamentRepositoryPort savePort) {
        this.savePort = savePort;
    }

    @Override
    // Le paso al método crear un torneo para crear, y un id de organizador
    public Tournament create(Tournament t, Long organizerId) {

        // Validamos el dominio antes de mandarlo revisar.
        Tournament toCreate = Tournament.create(t);
        return savePort.save(toCreate, organizerId);
    }

   }
