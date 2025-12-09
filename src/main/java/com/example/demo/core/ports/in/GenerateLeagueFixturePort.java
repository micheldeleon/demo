package com.example.demo.core.ports.in;

public interface GenerateLeagueFixturePort {
    void generate(Long tournamentId, boolean doubleRound);
}
