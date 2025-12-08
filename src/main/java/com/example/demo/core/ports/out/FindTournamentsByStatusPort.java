package com.example.demo.core.ports.out;

import java.util.List;

import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentStatus;

/**
 * Puerto de salida para recuperar torneos segA�n su estado.
 */
public interface FindTournamentsByStatusPort {

    /**
     * Obtiene los torneos que coinciden con el estado indicado.
     *
     * @param status estado del torneo a filtrar
     * @return lista de torneos que tienen el estado solicitado
     */
    List<Tournament> findByStatus(TournamentStatus status);
}
