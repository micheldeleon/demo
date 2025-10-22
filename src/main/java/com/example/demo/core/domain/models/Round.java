package com.example.demo.core.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Round { // Jornada
    private Long id;
    private List<Match> matches; // duelos
}

