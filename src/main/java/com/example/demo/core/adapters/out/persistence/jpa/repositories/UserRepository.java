package com.example.demo.core.adapters.out.persistence.jpa.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.core.adapters.out.persistence.jpa.entities.UserEntity;
import com.example.demo.core.adapters.out.persistence.jpa.interfaces.UserRepositoryJpa;
import com.example.demo.core.adapters.out.persistence.jpa.mappers.UserMapper;
import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.UserRepositoryPort;

@Repository
public class UserRepository implements UserRepositoryPort {

    private final UserRepositoryJpa userRepositoryJpa;
    private final UserMapper userMapper = new UserMapper();

    public UserRepository(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public List<User> findAll() {
        Iterable<UserEntity> userEntities = userRepositoryJpa.findAll();
        List<User> users = new ArrayList<>();
        userEntities.forEach(user -> users.add(userMapper.toDomain(user)));
        return users;
    }

    @Override
    public void save(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public User findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
