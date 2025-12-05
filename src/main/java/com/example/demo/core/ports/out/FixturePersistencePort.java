package com.example.demo.core.ports.out;

import java.util.List;

import com.example.demo.core.domain.models.TournamentMatch;

public interface FixturePersistencePort {

    boolean hasMatches(Long tournamentId);

    List<Long> getTeamIdsForTournament(Long tournamentId);

    void saveMatches(List<TournamentMatch> matches);

    List<TournamentMatch> findByTournament(Long tournamentId);
}
