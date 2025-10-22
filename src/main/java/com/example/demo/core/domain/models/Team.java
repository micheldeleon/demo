package com.example.demo.core.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team { // Equipo
    private Long id;
    private List<Participant> participants; // participantes
    private User creator; // creador
}

