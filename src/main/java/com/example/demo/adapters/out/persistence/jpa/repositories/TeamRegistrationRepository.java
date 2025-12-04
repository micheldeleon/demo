package com.example.demo.adapters.out.persistence.jpa.repositories;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.adapters.in.api.dto.ParticipantRequest;
import com.example.demo.adapters.out.persistence.jpa.entities.ParticipantEntity;
import com.example.demo.adapters.out.persistence.jpa.entities.TeamEntity;
import com.example.demo.adapters.out.persistence.jpa.entities.TeamParticipantEntity;
import com.example.demo.adapters.out.persistence.jpa.entities.TournamentTeamEntity;
import com.example.demo.adapters.out.persistence.jpa.interfaces.ParticipantRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.interfaces.TeamParticipantRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.interfaces.TeamRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.interfaces.TournamentTeamRepositoryJpa;
import com.example.demo.core.ports.out.TeamRegistrationPort;

@Component
public class TeamRegistrationRepository implements TeamRegistrationPort {

    private final TeamRepositoryJpa teamRepositoryJpa;
    private final ParticipantRepositoryJpa participantRepositoryJpa;
    private final TeamParticipantRepositoryJpa teamParticipantRepositoryJpa;
    private final TournamentTeamRepositoryJpa tournamentTeamRepositoryJpa;

    public TeamRegistrationRepository(TeamRepositoryJpa teamRepositoryJpa,
            ParticipantRepositoryJpa participantRepositoryJpa,
            TeamParticipantRepositoryJpa teamParticipantRepositoryJpa,
            TournamentTeamRepositoryJpa tournamentTeamRepositoryJpa) {
        this.teamRepositoryJpa = teamRepositoryJpa;
        this.participantRepositoryJpa = participantRepositoryJpa;
        this.teamParticipantRepositoryJpa = teamParticipantRepositoryJpa;
        this.tournamentTeamRepositoryJpa = tournamentTeamRepositoryJpa;
    }

    @Override
    @Transactional
    public void registerTeam(Long tournamentId, Long creatorUserId, String teamName, Long disciplineId,
            List<ParticipantRequest> participants) {

        TeamEntity team = new TeamEntity();
        team.setName(teamName);
        team.setCreatorId(creatorUserId);
        team.setDisciplineId(disciplineId);
        team.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
        team = teamRepositoryJpa.save(team);

        List<TeamParticipantEntity> teamParticipantEntities = new ArrayList<>();
        for (ParticipantRequest participant : participants) {
            ParticipantEntity pEntity = new ParticipantEntity();
            pEntity.setFullName(participant.fullName());
            pEntity.setNationalId(participant.nationalId());
            pEntity = participantRepositoryJpa.save(pEntity);

            TeamParticipantEntity tp = new TeamParticipantEntity(team.getId(), pEntity.getId());
            teamParticipantEntities.add(tp);
        }
        teamParticipantRepositoryJpa.saveAll(teamParticipantEntities);

        TournamentTeamEntity tt = new TournamentTeamEntity();
        tt.setTournamentId(tournamentId);
        tt.setTeamId(team.getId());
        tt.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
        tournamentTeamRepositoryJpa.save(tt);
    }
}
