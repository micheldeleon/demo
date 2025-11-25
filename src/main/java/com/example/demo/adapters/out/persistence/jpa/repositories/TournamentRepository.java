package com.example.demo.adapters.out.persistence.jpa.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.adapters.out.persistence.jpa.entities.TournamentJpaEntity;
import com.example.demo.adapters.out.persistence.jpa.interfaces.TournamentRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.mappers.TournamentMapper;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.ports.out.TournamentRepositoryPort;

@Component
public class TournamentRepository implements TournamentRepositoryPort {

    private final TournamentRepositoryJpa tournamentRepositoryJpa;

    public TournamentRepository(TournamentRepositoryJpa tournamentRepositoryJpa) {
        this.tournamentRepositoryJpa = tournamentRepositoryJpa;
    }

    @Override
    @Transactional
    public Tournament save(Tournament entity, Long organizerId) {
        TournamentJpaEntity e = TournamentMapper.mapToEntity(entity, organizerId);
        e = tournamentRepositoryJpa.save(e);
        return TournamentMapper.mapToDomain(e);
    }

    @Override
    public Tournament findById(Long id) {
        return tournamentRepositoryJpa.findById(id)
                .map(TournamentMapper::mapToDomain)
                .orElse(null);
    }

    @Override
    public List<Tournament> findAll() {
        List<Tournament> tournaments = new ArrayList<>();
        for (TournamentJpaEntity entity : tournamentRepositoryJpa.findAll()) {
            tournaments.add(TournamentMapper.mapToDomain(entity));
        }
        return tournaments;
    }

}
