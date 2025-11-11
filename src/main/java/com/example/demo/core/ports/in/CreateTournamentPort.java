package com.example.demo.core.ports.in;

//import com.example.demo.domain.models.Tournament;
import com.example.demo.core.domain.models.Tournament;

public interface CreateTournamentPort {
    Tournament create(Tournament toCreate, Long organizerId);
}
