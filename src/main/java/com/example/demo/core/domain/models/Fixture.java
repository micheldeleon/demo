package com.example.demo.core.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fixture { // Fixture
    private Long id;
    private List<Round> rounds; // jornadas
}

