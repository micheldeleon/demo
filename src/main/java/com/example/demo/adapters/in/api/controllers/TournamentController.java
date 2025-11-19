package com.example.demo.adapters.in.api.controllers;

import com.example.demo.adapters.in.api.dto.CreateTournamentRequest;
import com.example.demo.adapters.in.api.dto.TournamentResponse;
import com.example.demo.adapters.in.api.mappers.TournamentMapper;
import com.example.demo.core.application.usecase.CreateTournamentUseCase;
import com.example.demo.core.domain.models.Tournament;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final CreateTournamentUseCase createTournamentUseCase;

    public TournamentController(CreateTournamentUseCase useCase) {
        this.createTournamentUseCase = useCase;
    }

    // Por ahora organizerId viene en el path. Luego lo obtendremos del JWT.
    @PostMapping("/organizer/{organizerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TournamentResponse create(
            @PathVariable Long organizerId,
            @Valid @RequestBody CreateTournamentRequest request) {
        Tournament saved = createTournamentUseCase.create(TournamentMapper.toDomain(request), organizerId);
        return TournamentMapper.toResponse(saved);
    }
}
