package com.example.demo.adapters.in.api.dto;

import java.util.Date;

import com.example.demo.core.domain.models.TournamentStatus;

public record TournamentSummaryResponse(
        Long id,
        String name,
        Long disciplineId,
        Date startAt,
        Date endAt,
        Date registrationDeadline,
        boolean privateTournament,
        String prize,
        double registrationCost,
        TournamentStatus status) {
}
