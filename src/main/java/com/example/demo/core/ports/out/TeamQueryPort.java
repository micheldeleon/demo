package com.example.demo.core.ports.out;

import java.util.List;

public interface TeamQueryPort {
    List<TeamSummary> findTeamsByTournament(Long tournamentId);

    record TeamSummary(Long id, String name) {}
}
