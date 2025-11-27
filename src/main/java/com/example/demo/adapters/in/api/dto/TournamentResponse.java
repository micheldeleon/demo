package com.example.demo.adapters.in.api.dto;

import java.util.Date;

public record TournamentResponse(
    Long id,
    Long disciplineId,
    int teamsInscribed,
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
