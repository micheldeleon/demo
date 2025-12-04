package com.example.demo.adapters.out.persistence.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.adapters.out.persistence.jpa.entities.TournamentTeamEntity;
import com.example.demo.adapters.out.persistence.jpa.entities.TournamentTeamKey;

@Repository
public interface TournamentTeamRepositoryJpa extends JpaRepository<TournamentTeamEntity, TournamentTeamKey> {
}
