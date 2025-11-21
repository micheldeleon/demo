package com.example.demo.adapters.in.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adapters.in.api.dto.CreateTournamentRequest;
import com.example.demo.adapters.in.api.dto.TournamentResponse;
import com.example.demo.adapters.in.api.mappers.TournamentMapper;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.ports.in.CreateTournamentPort;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final CreateTournamentPort createTournamentPort;

    public TournamentController(CreateTournamentPort useCase) {
        this.createTournamentPort = useCase;
    }

    // Por ahora organizerId viene en el path. Luego lo obtendremos del JWT.
    @PostMapping("/organizer/{organizerId}")
    public ResponseEntity<TournamentResponse> create(
            @PathVariable Long organizerId,
            @Valid @RequestBody CreateTournamentRequest request) {

        Tournament saved = createTournamentPort.create(
                TournamentMapper.toDomain(request),
                organizerId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TournamentMapper.toResponse(saved));
    }
}
