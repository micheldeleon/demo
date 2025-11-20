package com.example.demo.adapters.out.persistence.jpa.repositories;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.adapters.out.persistence.jpa.entities.TournamentJpaEntity;
import com.example.demo.adapters.out.persistence.jpa.interfaces.TournamentRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.mappers.TournamentMapper;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.ports.out.SaveTournamentPort;

@Component
public class SaveTournamentAdapter implements SaveTournamentPort {

    private final TournamentRepositoryJpa tournamentRepositoryJpa;

    public SaveTournamentAdapter(TournamentRepositoryJpa tournamentRepositoryJpa) {
        this.tournamentRepositoryJpa = tournamentRepositoryJpa;
    }

    @Override
    @Transactional
    public Tournament save(Tournament t, Long organizerId) {
        TournamentJpaEntity e = TournamentMapper.mapToEntity(t, organizerId);
        e = tournamentRepositoryJpa.save(e);
        // devolvemos el mismo domain con el id persistido
        if (t.getId() == null)
            t.setId(e.getId());
        return t;
    }

}
