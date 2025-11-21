package com.example.demo.adapters.out.persistence.jpa.repositories;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.adapters.out.persistence.jpa.entities.TournamentJpaEntity;
import com.example.demo.adapters.out.persistence.jpa.interfaces.TournamentRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.mappers.TournamentMapper;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentStatus;
import com.example.demo.core.ports.out.FindTournamentsPort;

@Component
public class FindTournamentsAdapter implements FindTournamentsPort {

    private final TournamentRepositoryJpa tournamentRepositoryJpa;

    public FindTournamentsAdapter(TournamentRepositoryJpa tournamentRepositoryJpa) {
        this.tournamentRepositoryJpa = tournamentRepositoryJpa;
    }

    @Override
    public List<Tournament> findByFilters(
            TournamentStatus status,
            Long disciplineId,
            String nameContains,
            Date startFrom,
            Date startTo,
            Boolean withPrize,
            Boolean withRegistrationCost) {

        OffsetDateTime startFromOdt = toOdt(startFrom);
        OffsetDateTime startToOdt = toOdt(startTo);
        String statusValue = status != null ? status.name() : null;

        String namePattern = (nameContains == null || nameContains.isBlank())
                ? ""
                : "%" + nameContains.toLowerCase() + "%";

        List<TournamentJpaEntity> entities = tournamentRepositoryJpa.findByFilters(
                statusValue,
                disciplineId,
                namePattern,
                startFromOdt,
                startToOdt,
                withPrize,
                withRegistrationCost);

        return entities.stream()
                .map(TournamentMapper::mapToDomain)
                .toList();
    }

    private OffsetDateTime toOdt(Date date) {
        return date == null ? null : date.toInstant().atOffset(ZoneOffset.UTC);
    }
}
