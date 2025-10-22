package com.example.demo.core.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discipline { // Disciplina
    private Long id;
    private boolean collective; // esColectivo
    private String name; // nombre
}

