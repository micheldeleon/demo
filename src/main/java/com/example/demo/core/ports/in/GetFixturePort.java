package com.example.demo.core.ports.in;

import java.util.List;

import com.example.demo.core.domain.models.TournamentMatch;

public interface GetFixturePort {
    List<TournamentMatch> getFixture(Long tournamentId);
}
