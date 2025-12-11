package com.example.demo.core.ports.in;

import java.util.Date;

public interface CancelTournamentPort {

    //Esta interfaz ofrece este metodo para cancelar un torneo que lo va a utilizar el caso de uso.
    CancelTournamentResult cancel(Long tournamentId, String userEmail);

    //Clase interna para devolver el resultado de la cancelación.
    record CancelTournamentResult(Long tournamentId, String canceledByName, Date canceledAt) {
    }
}
