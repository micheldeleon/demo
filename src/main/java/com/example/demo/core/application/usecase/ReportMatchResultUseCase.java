package com.example.demo.core.application.usecase;

import java.util.Date;

import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentMatch;
import com.example.demo.core.domain.models.TournamentStatus;
import com.example.demo.core.domain.models.Formats.EliminationFormat;
import com.example.demo.core.ports.in.ReportMatchResultPort;
import com.example.demo.core.ports.out.FixturePersistencePort;
import com.example.demo.core.ports.out.TournamentRepositoryPort;

public class ReportMatchResultUseCase implements ReportMatchResultPort {

    private static final String STATUS_FINISHED = "FINISHED";
    private static final String STATUS_PENDING = "PENDING";

    private final FixturePersistencePort fixturePersistencePort;
    private final TournamentRepositoryPort tournamentRepositoryPort;

    public ReportMatchResultUseCase(FixturePersistencePort fixturePersistencePort,
            TournamentRepositoryPort tournamentRepositoryPort) {
        this.fixturePersistencePort = fixturePersistencePort;
        this.tournamentRepositoryPort = tournamentRepositoryPort;
    }

    @Override
    public void reportResult(Long tournamentId, Long matchId, Integer scoreHome, Integer scoreAway, Long winnerTeamId) {
        if (tournamentId == null || matchId == null || winnerTeamId == null) {
            throw new IllegalArgumentException("tournamentId, matchId y winnerTeamId son requeridos");
        }

        TournamentMatch match = fixturePersistencePort.getMatch(matchId);
        if (match == null || !tournamentId.equals(match.getTournamentId())) {
            throw new IllegalArgumentException("Partido no encontrado para el torneo");
        }

        Tournament tournament = tournamentRepositoryPort.findById(tournamentId);
        if (tournament == null) {
            throw new IllegalArgumentException("Torneo no encontrado");
        }
        if (!(tournament.getFormat() instanceof EliminationFormat)) {
            throw new IllegalStateException("El formato del torneo no es eliminatorio");
        }
        if (tournament.getStatus() != TournamentStatus.INICIADO) {
            throw new IllegalStateException("El torneo no está en estado INICIADO");
        }

        if (match.getStatus() != null && STATUS_FINISHED.equals(match.getStatus())) {
            throw new IllegalStateException("El partido ya fue finalizado");
        }

        // Validar que el ganador sea uno de los equipos del partido
        if (!winnerTeamId.equals(match.getHomeTeamId()) && !winnerTeamId.equals(match.getAwayTeamId())) {
            throw new IllegalArgumentException("El ganador no corresponde a los equipos del partido");
        }

        // No permitimos empates; si se pasan scores, deben reflejar ganador
        if (scoreHome != null && scoreAway != null) {
            if (scoreHome.equals(scoreAway)) {
                throw new IllegalArgumentException("No se permiten empates en eliminatorio");
            }
            if (winnerTeamId.equals(match.getHomeTeamId()) && scoreHome < scoreAway) {
                throw new IllegalArgumentException("El ganador no coincide con los puntajes");
            }
            if (winnerTeamId.equals(match.getAwayTeamId()) && scoreAway < scoreHome) {
                throw new IllegalArgumentException("El ganador no coincide con los puntajes");
            }
        }

        Date now = new Date();
        match.setScoreHome(scoreHome);
        match.setScoreAway(scoreAway);
        match.setWinnerTeamId(winnerTeamId);
        match.setStatus(STATUS_FINISHED);
        match.setUpdatedAt(now);
        fixturePersistencePort.saveMatch(match);

        // Avanzar al siguiente partido en la ronda. Lo defino abajo en un método aparte para
        // mayor claridad.
        advanceToNextRound(match, winnerTeamId, now);
    }

    private void advanceToNextRound(TournamentMatch match, Long winnerTeamId, Date now) {
        int nextRound = match.getRound() + 1;
        int nextMatchNumber = (match.getMatchNumber() + 1) / 2;
        boolean winnerGoesAsHome = (match.getMatchNumber() % 2 == 1); // impar -> home, par -> away

        TournamentMatch nextMatch = fixturePersistencePort.findByTournamentRoundAndNumber(
                match.getTournamentId(), nextRound, nextMatchNumber);

        if (nextMatch == null) {
            nextMatch = new TournamentMatch();
            nextMatch.setTournamentId(match.getTournamentId());
            nextMatch.setRound(nextRound);
            nextMatch.setMatchNumber(nextMatchNumber);
            nextMatch.setStatus(STATUS_PENDING);
            nextMatch.setCreatedAt(now);
        }

        if (winnerGoesAsHome) {
            nextMatch.setHomeTeamId(winnerTeamId);
        } else {
            nextMatch.setAwayTeamId(winnerTeamId);
        }

        nextMatch.setUpdatedAt(now);
        fixturePersistencePort.saveMatch(nextMatch);
    }
}
