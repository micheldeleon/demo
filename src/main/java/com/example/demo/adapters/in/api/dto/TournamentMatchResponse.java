package com.example.demo.adapters.in.api.dto;

import java.util.Date;

public record TournamentMatchResponse(
        Long id,
        int round,
        int matchNumber,
        Long homeTeamId,
        Long awayTeamId,
        Long winnerTeamId,
        String status,
        Integer scoreHome,
        Integer scoreAway,
        Date scheduledAt) {
}
