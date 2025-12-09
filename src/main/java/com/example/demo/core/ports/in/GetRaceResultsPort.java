package com.example.demo.core.ports.in;

import java.util.List;

import com.example.demo.core.domain.models.RaceResult;

public interface GetRaceResultsPort {
    List<RaceResult> list(Long tournamentId);
}
