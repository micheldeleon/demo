package com.example.demo.adapters.out.persistence.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.adapters.out.persistence.jpa.entities.TeamEntity;

@Repository
public interface TeamRepositoryJpa extends JpaRepository<TeamEntity, Long> {
}
