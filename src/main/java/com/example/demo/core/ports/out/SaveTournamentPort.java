package com.example.demo.core.ports.out;

import com.example.demo.core.domain.models.Tournament;

public interface SaveTournamentPort {
    Tournament save(Tournament tournament, Long organizerId);
}
