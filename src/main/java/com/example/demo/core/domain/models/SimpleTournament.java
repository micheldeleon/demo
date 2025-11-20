package com.example.demo.core.domain.models;

import java.util.Date;
import java.util.List;

// Tournament es abstracta. Creamos una concreta mínima para poder instanciar.
public class SimpleTournament extends Tournament {

    public SimpleTournament(
        Long id,
        Discipline discipline,
        String name,
        Date createdAt,
        Date endAt,
        Date startAt,
        boolean privateTournament,
        String password,
        int minParticipantsPerTeam,
        int maxParticipantsPerTeam,
        Date registrationDeadline,
        String prize,
        double registrationCost,
        User organizer,
        int minParticipantsPerTournament,
        int maxParticipantsPerTournament,
        List<Team> teams
    ) {
        setId(id);
        setDiscipline(discipline);
        setName(name);
        setCreatedAt(createdAt);
        setEndAt(endAt);
        setStartAt(startAt);
        setPrivateTournament(privateTournament);
        setPassword(password);
        setMinParticipantsPerTeam(minParticipantsPerTeam);
        setMaxParticipantsPerTeam(maxParticipantsPerTeam);
        setRegistrationDeadline(registrationDeadline);
        setPrize(prize);
        setRegistrationCost(registrationCost);
        setOrganizer(organizer);
        setMinParticipantsPerTournament(minParticipantsPerTournament);
        setMaxParticipantsPerTournament(maxParticipantsPerTournament);
        setTeams(teams);
    }

    // Si querés un ctor vacío, podés agregarlo manualmente:
    public SimpleTournament() {}
}
