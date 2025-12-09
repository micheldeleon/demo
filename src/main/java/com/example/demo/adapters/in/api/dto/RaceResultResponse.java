package com.example.demo.adapters.in.api.dto;

public record RaceResultResponse(
        Long teamId,
        String teamName,
        Long timeMillis,
        Integer position) {
}
