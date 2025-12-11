package com.example.demo.adapters.in.api.dto;

import java.util.Date;

import com.example.demo.core.domain.models.TournamentStatus;

public record CancelTournamentResponse(
        Long tournamentId,
        TournamentStatus status,
        Date canceledAt,
        String canceledBy,
        String message) {
}
