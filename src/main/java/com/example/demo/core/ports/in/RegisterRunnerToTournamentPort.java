package com.example.demo.core.ports.in;

public interface RegisterRunnerToTournamentPort {
    void register(Long tournamentId, String userEmail, com.example.demo.adapters.in.api.dto.RunnerRegistrationRequest request);
}
