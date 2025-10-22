package com.example.demo.core.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueFormat extends NonUniqueFormat { // Liga
    private int winPoints;   // puntosVictoria
    private int drawPoints;  // puntosEmpate
    private int lossPoints;  // puntosDerrota
    private boolean hasTiebreak; // tieneEmpate
}

