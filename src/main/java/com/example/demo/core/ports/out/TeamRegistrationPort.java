package com.example.demo.core.ports.out;

import java.util.List;

import com.example.demo.adapters.in.api.dto.ParticipantRequest;

public interface TeamRegistrationPort {
    void registerTeam(Long tournamentId, Long creatorUserId, String teamName, Long disciplineId,
            List<ParticipantRequest> participants);
}
