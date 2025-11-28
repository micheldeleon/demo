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
import com.example.demo.adapters.in.api.dto.RegisterToTournamentRequest;
import com.example.demo.adapters.in.api.dto.TournamentResponse;
import com.example.demo.adapters.in.api.dto.TournamentSummaryResponse;
import com.example.demo.adapters.in.api.mappers.TournamentMapper;
import com.example.demo.adapters.in.api.mappers.TournamentSummaryMapper;
import com.example.demo.core.application.usecase.GetTournamentById;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentStatus;
import com.example.demo.core.ports.in.CreateTournamentPort;
import com.example.demo.core.ports.in.GetAllTournamentsPort;
import com.example.demo.core.ports.in.ListPublicTournamentsPort;
import com.example.demo.core.ports.in.ListTournamentsByStatusPort;
import com.example.demo.core.ports.in.RegisterToTournamentPort;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final CreateTournamentPort createTournamentPort;
    private final GetAllTournamentsPort getAllTournamentsPort;
    private final GetTournamentById getTournamentById;
    private final ListPublicTournamentsPort listPublicTournamentsPort;
    private final ListTournamentsByStatusPort listTournamentsByStatusPort;
    private final RegisterToTournamentPort registerToTournamentPort;

    public TournamentController(CreateTournamentPort createTournamentPort,
            GetAllTournamentsPort getAllTournamentsPort,
            GetTournamentById getTournamentById,
            ListPublicTournamentsPort listPublicTournamentsPort,
            ListTournamentsByStatusPort listTournamentsByStatusPort,
            RegisterToTournamentPort registerToTournamentPort) {
        this.createTournamentPort = createTournamentPort;
        this.getAllTournamentsPort = getAllTournamentsPort;
        this.getTournamentById = getTournamentById;
        this.listPublicTournamentsPort = listPublicTournamentsPort;
        this.listTournamentsByStatusPort = listTournamentsByStatusPort;
        this.registerToTournamentPort = registerToTournamentPort;
    }

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
    public List<TournamentSummaryResponse> listByStatus(@RequestParam TournamentStatus status) {
        return listTournamentsByStatusPort.listByStatus(status)
                .stream()
                .map(TournamentSummaryMapper::toResponse)
                .toList();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTournaments() {
        try {
            return ResponseEntity.ok(getAllTournamentsPort.getAllTournaments());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTournamentById(@PathVariable Long id) {
        try {
            Tournament tournament = getTournamentById.getTournamentById(id);
            return ResponseEntity.ok(TournamentMapper.toResponse(tournament));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/register")
    public ResponseEntity<?> registerToTournament(@PathVariable Long id,
            @Valid @RequestBody RegisterToTournamentRequest request) {
        try {
            registerToTournamentPort.register(id, request.userId());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
