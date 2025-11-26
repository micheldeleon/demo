package com.example.demo.core.ports.out;

import java.util.List;

import com.example.demo.core.domain.models.Tournament;

public interface TournamentRepositoryPort {
    Tournament save(Tournament tournament, Long organizerId);

    Tournament findById(Long id);

    List<Tournament> findAll();
}
