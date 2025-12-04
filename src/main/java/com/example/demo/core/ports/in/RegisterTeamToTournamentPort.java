package com.example.demo.core.ports.in;

import java.util.List;

import com.example.demo.adapters.in.api.dto.ParticipantRequest;

public interface RegisterTeamToTournamentPort {
    void registerTeam(Long tournamentId, Long userId, String teamName, List<ParticipantRequest> participants);
}
