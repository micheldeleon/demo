package com.example.demo.adapters.out.persistence.jpa.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.adapters.out.persistence.jpa.entities.UserEntity;
import com.example.demo.core.domain.models.User;

public interface UserRepositoryJpa extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
