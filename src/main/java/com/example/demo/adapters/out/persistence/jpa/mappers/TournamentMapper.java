package com.example.demo.adapters.out.persistence.jpa.mappers;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import com.example.demo.adapters.out.persistence.jpa.entities.TournamentJpaEntity;
import com.example.demo.core.domain.models.Discipline;
import com.example.demo.core.domain.models.SimpleTournament;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentStatus;

public class TournamentMapper {
    // -------------------- mapping --------------------

    public static TournamentJpaEntity mapToEntity(Tournament t, Long organizerId) {
        TournamentJpaEntity e = new TournamentJpaEntity();

        e.setId(t.getId());
        e.setName(t.getName());

        // Discipline en dominio -> disciplineId (Long) en JPA
        e.setDisciplineId(t.getDiscipline() != null ? t.getDiscipline().getId() : null);

        // Si tenés Format en dominio, mapear a formatId (si no, dejar null)
        // e.setFormatId(t.getFormat() != null ? t.getFormat().getId() : null);

        // organizerId viene por parámetro del puerto
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

        // ¡OJO! nombres en la entidad:
        e.setMinParticipantsTournament(t.getMinParticipantsPerTournament());
        e.setMaxParticipantsTournament(t.getMaxParticipantsPerTournament());

        e.setPrize(t.getPrize());
        e.setRegistrationCost(BigDecimal.valueOf(t.getRegistrationCost()));
        e.setStatus(t.getStatus() != null ? t.getStatus().name() : null);

        return e;
    }

    public static Tournament mapToDomain(TournamentJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        SimpleTournament t = new SimpleTournament();
        t.setId(entity.getId());
        t.setName(entity.getName());
        t.setDiscipline(entity.getDisciplineId() != null
                ? new Discipline(entity.getDisciplineId(), false, null, null)
                : null);
        t.setCreatedAt(fromOdt(entity.getCreatedAt()));
        t.setStartAt(fromOdt(entity.getStartAt()));
        t.setEndAt(fromOdt(entity.getEndAt()));
        t.setRegistrationDeadline(fromOdt(entity.getRegistrationDeadline()));
        t.setPrivateTournament(entity.isPrivateTournament());
        t.setPassword(entity.getPassword());
        t.setMinParticipantsPerTeam(entity.getMinParticipantsPerTeam());
        t.setMaxParticipantsPerTeam(entity.getMaxParticipantsPerTeam());
        t.setMinParticipantsPerTournament(entity.getMinParticipantsTournament() != null
                ? entity.getMinParticipantsTournament()
                : 0);
        t.setMaxParticipantsPerTournament(entity.getMaxParticipantsTournament() != null
                ? entity.getMaxParticipantsTournament()
                : 0);
        t.setPrize(entity.getPrize());
        t.setRegistrationCost(entity.getRegistrationCost() != null
                ? entity.getRegistrationCost().doubleValue()
                : 0);
        if (entity.getStatus() != null) {
            t.setStatus(TournamentStatus.valueOf(entity.getStatus()));
        }
        return t;
    }

    private static OffsetDateTime toOdt(Date d) {
        return (d == null) ? null : d.toInstant().atOffset(ZoneOffset.UTC);
    }

    private static Date fromOdt(OffsetDateTime odt) {
        return odt == null ? null : Date.from(odt.toInstant());
    }
}
