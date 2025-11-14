package com.example.demo.adapters.in.api.mappers;

import com.example.demo.adapters.in.api.dto.CreateTournamentRequest;
import com.example.demo.adapters.in.api.dto.TournamentResponse;
import com.example.demo.core.domain.models.*;

import java.util.Date;

// Centraliza la conversión entre el JSON de la API y tu modelo de dominio.

public class TournamentMapper {

    public static Tournament toDomain(CreateTournamentRequest r) {
        // solo seteamos Discipline con el ID (lo demás lo podés cargar luego)
        Discipline d = new Discipline(r.disciplineId(), false, null);

        return new SimpleTournament(
            null,                  // id lo asigna DB
            d,
            r.name(),
            null,                  // createdAt se setea en el use case si viene null
            r.endAt(),
            r.startAt(),
            r.privateTournament(),
            r.password(),
            r.minParticipantsPerTeam(),
            r.maxParticipantsPerTeam(),
            r.registrationDeadline(),
            r.prize(),
            r.registrationCost(),
            null,                  // organizer (por ahora no lo seteamos aquí)
            r.minParticipantsPerTournament(),
            r.maxParticipantsPerTournament(),
            null                   // teams
        );
    }

    public static TournamentResponse toResponse(Tournament t) {
        return new TournamentResponse(
            t.getId(),
            t.getDiscipline() != null ? t.getDiscipline().getId() : null,
            t.getName(),
            t.getCreatedAt(),
            t.getStartAt(),
            t.getEndAt(),
            t.getRegistrationDeadline(),
            t.isPrivateTournament(),
            t.getPrize(),
            t.getRegistrationCost(),
            t.getMinParticipantsPerTeam(),
            t.getMaxParticipantsPerTeam(),
            t.getMinParticipantsPerTournament(),
            t.getMaxParticipantsPerTournament()
        );
    }
}
