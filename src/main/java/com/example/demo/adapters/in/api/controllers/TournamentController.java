package com.example.demo.adapters.in.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adapters.in.api.dto.CreateTournamentRequest;
import com.example.demo.adapters.in.api.dto.TournamentResponse;
import com.example.demo.adapters.in.api.mappers.TournamentMapper;
import com.example.demo.core.application.usecase.GetTournamentByIdUseCase;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.ports.in.CreateTournamentPort;
import com.example.demo.core.ports.in.GetTournamentPort;
import com.example.demo.core.ports.in.GetAllTournamentsPort;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final CreateTournamentPort createTournamentPort;
    private final GetTournamentPort getTournamentPort;
    private final GetAllTournamentsPort getAllTournamentsPort;
    private final GetTournamentByIdUseCase getTournamentById;

    public TournamentController(CreateTournamentPort useCase, GetTournamentPort getUseCase,
            GetAllTournamentsPort getAllUseCase, GetTournamentByIdUseCase getByIdUseCase) {
        this.getAllTournamentsPort = getAllUseCase;
        this.createTournamentPort = useCase;
        this.getTournamentPort = getUseCase;
        this.getTournamentById = getByIdUseCase;
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
    // @GetMapping(params = { "id"})
    // public ResponseEntity<?> getTournamentsByUserIdandEmail(
    // @RequestParam Long id) {
    // try {
    // List<Tournament> tournaments =
    // getTournamentPort.getSubscribedTournaments(id);
    // return ResponseEntity.ok(tournaments);
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body(e.getMessage());
    // }
    // }

}
