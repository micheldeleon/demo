package com.example.demo.core.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match { // Duelo
    private Long id;
    private int homeTeamScore; // resultadoEquipoLocal
    private int awayTeamScore; // resultadoEquipoVisita
}

