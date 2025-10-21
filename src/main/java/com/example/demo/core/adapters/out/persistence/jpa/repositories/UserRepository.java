package com.example.demo.core.adapters.out.persistence.jpa.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.core.adapters.out.persistence.jpa.entities.UserEntity;
import com.example.demo.core.adapters.out.persistence.jpa.interfaces.UserRepositoryJpa;
import com.example.demo.core.adapters.out.persistence.jpa.mappers.UserMapper;
import com.example.demo.core.domain.models.User;

public class UserRepository {

    private final UserRepositoryJpa userRepositoryJpa;
    private final UserMapper userMapper = new UserMapper();

    public UserRepository(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }
   public List<User> findAll() {
        Iterable<UserEntity> userEntities = userRepositoryJpa.findAll();
        List<User> users = new ArrayList<>();
        userEntities.forEach(user -> users.add(userMapper.toDomain(user)));
        return users;
    }
}
