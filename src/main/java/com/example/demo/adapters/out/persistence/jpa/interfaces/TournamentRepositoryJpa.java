package com.example.demo.adapters.out.persistence.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.adapters.out.persistence.jpa.entities.TournamentJpaEntity;

public interface TournamentRepositoryJpa extends JpaRepository<TournamentJpaEntity, Long> {
}
