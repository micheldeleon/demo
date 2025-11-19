package com.example.demo.core.domain.models;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Tournament { // Torneo
    private Long id;
    private List<Team> teams; // equipos
    private Discipline discipline; // disciplina
    private String name; // nombre
    private Date createdAt; // fechaCreacion
    private Date endAt; // fechaFin
    private Date startAt; // fechaInicio
    private boolean privateTournament; // esPrivado
    private String password; // password
    private int minParticipantsPerTeam; // cantMinParticipantesXEquipo
    private int maxParticipantsPerTeam; // cantMaxParticipantesXEquipo
    private Date registrationDeadline; // fechaLimiteInscripciones
    private String prize; // premio
    private double registrationCost; // costoInscripcion
    private User organizer; // organizador
    private int minParticipantsPerTournament; // cantMinParticipantesXTorneo
    private int maxParticipantsPerTournament; // cantMaxParticipantesXTorneo
    private TournamentStatus status; // estado
}



