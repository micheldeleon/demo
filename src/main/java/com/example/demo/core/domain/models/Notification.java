package com.example.demo.core.domain.models;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification { // Notificacion
    private Long id;
    private User user;     // usuario
    private String message; // mensaje
    private Date date;     // fecha
    private LocalDateTime readAt;
}

