package com.example.demo.adapters.in.api.dto;

import java.util.Date;

import com.example.demo.core.domain.models.Format;

public record TournamentResponse(
    Long id,
    Format format,
    Long disciplineId,
    String name,
    Date createdAt,
    Date startAt,
    Date endAt,
    Date registrationDeadline,
    boolean privateTournament,
    String prize,
    double registrationCost,
    int minParticipantsPerTeam,
    int maxParticipantsPerTeam,
    int minParticipantsPerTournament,
    int maxParticipantsPerTournament
) {}
