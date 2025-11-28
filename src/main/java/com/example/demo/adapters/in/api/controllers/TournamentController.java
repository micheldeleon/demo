package com.example.demo.adapters.in.api.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adapters.in.api.dto.CreateTournamentRequest;
import com.example.demo.adapters.in.api.dto.TournamentResponse;
import com.example.demo.adapters.in.api.dto.TournamentSummaryResponse;
import com.example.demo.adapters.in.api.mappers.TournamentMapper;
import com.example.demo.adapters.in.api.mappers.TournamentSummaryMapper;
import com.example.demo.core.application.usecase.CreateTournamentUseCase;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentStatus;
import com.example.demo.core.ports.in.ListPublicTournamentsPort;
import com.example.demo.core.ports.in.ListTournamentsByStatusPort;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final CreateTournamentUseCase createTournamentUseCase;
    private final ListPublicTournamentsPort listPublicTournamentsPort;
    private final ListTournamentsByStatusPort listTournamentsByStatusPort;

    public TournamentController(CreateTournamentUseCase useCase,
            ListPublicTournamentsPort listPublicTournamentsPort,
            ListTournamentsByStatusPort listTournamentsByStatusPort) {
        this.createTournamentUseCase = useCase;
        this.listPublicTournamentsPort = listPublicTournamentsPort;
        this.listTournamentsByStatusPort = listTournamentsByStatusPort;
    }

    // Por ahora organizerId viene en el path. Luego lo obtendremos del JWT.
    @PostMapping("/organizer/{organizerId}")
    public ResponseEntity<TournamentResponse> create(
            @PathVariable Long organizerId,
            @Valid @RequestBody CreateTournamentRequest request) {

        Tournament saved = createTournamentUseCase.create(
                TournamentMapper.toDomain(request),
                organizerId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TournamentMapper.toResponse(saved));
    }

    @GetMapping("/public")
    public List<TournamentSummaryResponse> listPublicTournaments(
            @RequestParam(required = false) TournamentStatus status,
            @RequestParam(required = false) Long disciplineId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startTo,
            @RequestParam(required = false) Boolean withPrize,
            @RequestParam(required = false) Boolean withRegistrationCost) {

        return listPublicTournamentsPort.list(
                status,
                disciplineId,
                name,
                startFrom,
                startTo,
                withPrize,
                withRegistrationCost)
                .stream()
                .map(TournamentSummaryMapper::toResponse)
                .toList();
    }

    @GetMapping("/status")
    public List<TournamentSummaryResponse> listByStatus(
            @RequestParam TournamentStatus status) {
        return listTournamentsByStatusPort.listByStatus(status)
                .stream()
                .map(TournamentSummaryMapper::toResponse)
                .toList();
    }
}
