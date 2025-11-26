package com.example.demo.adapters.out.persistence.jpa.mappers;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import com.example.demo.adapters.out.persistence.jpa.entities.TournamentJpaEntity;
import com.example.demo.core.domain.models.Discipline;
import com.example.demo.core.domain.models.Tournament;

public class TournamentMapper {
    // -------------------- mapping --------------------

    public static TournamentJpaEntity mapToEntity(Tournament t, Long organizerId) {
        TournamentJpaEntity e = new TournamentJpaEntity();

        e.setId(t.getId());
        e.setName(t.getName());

        // Discipline en dominio -> disciplineId (Long) en JPA
        e.setDisciplineId(t.getDiscipline() != null ? t.getDiscipline().getId() : null);

        // Si tenes Format en dominio, mapear a formatId (si no, dejar null)
        // e.setFormatId(t.getFormat() != null ? t.getFormat().getId() : null);

        // organizerId viene por parametro del puerto
        e.setOrganizerId(organizerId);

        // Date -> OffsetDateTime
        e.setCreatedAt(toOdt(t.getCreatedAt()));
        e.setStartAt(toOdt(t.getStartAt()));
        e.setEndAt(toOdt(t.getEndAt()));
        e.setRegistrationDeadline(toOdt(t.getRegistrationDeadline()));

        e.setPrivateTournament(t.isPrivateTournament());
        e.setPassword(t.getPassword());

        e.setMinParticipantsPerTeam(t.getMinParticipantsPerTeam());
        e.setMaxParticipantsPerTeam(t.getMaxParticipantsPerTeam());

        // OJO! nombres en la entidad:
        e.setMinParticipantsTournament(t.getMinParticipantsPerTournament());
        e.setMaxParticipantsTournament(t.getMaxParticipantsPerTournament());

        e.setPrize(t.getPrize());
        e.setRegistrationCost(BigDecimal.valueOf(t.getRegistrationCost()));

        return e;
    }

    public static Tournament mapToDomain(TournamentJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        Tournament tournament = new Tournament();
        tournament.setId(entity.getId());
        tournament.setTeams(new ArrayList<>());
        tournament.setDiscipline(new Discipline(entity.getDisciplineId(), false, null, null));
        tournament.setName(entity.getName());
        tournament.setCreatedAt(fromOdt(entity.getCreatedAt()));
        tournament.setEndAt(fromOdt(entity.getEndAt()));
        tournament.setStartAt(fromOdt(entity.getStartAt()));
        tournament.setPrivateTournament(entity.isPrivateTournament());
        tournament.setPassword(entity.getPassword());
        tournament.setMinParticipantsPerTeam(entity.getMinParticipantsPerTeam());
        tournament.setMaxParticipantsPerTeam(entity.getMaxParticipantsPerTeam());
        tournament.setRegistrationDeadline(fromOdt(entity.getRegistrationDeadline()));
        tournament.setPrize(entity.getPrize());
        tournament.setRegistrationCost(entity.getRegistrationCost() != null
                ? entity.getRegistrationCost().doubleValue()
                : 0D);
        tournament.setOrganizer(null);
        tournament.setMinParticipantsPerTournament(entity.getMinParticipantsTournament());
        tournament.setMaxParticipantsPerTournament(entity.getMaxParticipantsTournament());
        tournament.setStatus(null);
        return tournament;
    }

    private static OffsetDateTime toOdt(Date d) {
        return (d == null) ? null : d.toInstant().atOffset(ZoneOffset.UTC);
    }

    private static Date fromOdt(OffsetDateTime odt) {
        return (odt == null) ? null : Date.from(odt.toInstant());
    }
}
