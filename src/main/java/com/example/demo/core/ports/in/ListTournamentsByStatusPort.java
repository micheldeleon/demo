package com.example.demo.core.ports.in;

import java.util.List;

import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentStatus;

/**
 * Puerto de entrada para consultar torneos segA�n su estado actual.
 */
public interface ListTournamentsByStatusPort {

    /**
     * Lista todos los torneos que estA�n en el estado indicado.
     *
     * @param status estado requerido del torneo (ABIERTO, INICIADO, etc.)
     * @return colecciA�n de torneos que cumplen con el estado
     */
    List<Tournament> listByStatus(TournamentStatus status);
}
