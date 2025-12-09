package com.example.demo.core.ports.in;

import java.util.List;

import com.example.demo.core.ports.in.models.LeagueStanding;

public interface GetLeagueStandingsPort {
    List<LeagueStanding> list(Long tournamentId);
}
