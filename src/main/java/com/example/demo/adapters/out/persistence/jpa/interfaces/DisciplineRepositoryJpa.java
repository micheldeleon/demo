package com.example.demo.adapters.out.persistence.jpa.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.adapters.out.persistence.jpa.entities.DisciplineEntity;

public interface DisciplineRepositoryJpa extends CrudRepository<DisciplineEntity, Long> {
}
