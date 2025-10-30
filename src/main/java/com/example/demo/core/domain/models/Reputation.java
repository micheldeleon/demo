package com.example.demo.core.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reputation {
    private User user; // usuario que da la reputacion
    private Organizer organizer; // organizador que recibe la reputacion
    private int score; // puntaje de reputacion
}
