package com.example.demo.adapters.out.persistence.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.adapters.out.persistence.jpa.entities.ParticipantEntity;

@Repository
public interface ParticipantRepositoryJpa extends JpaRepository<ParticipantEntity, Long> {
}
