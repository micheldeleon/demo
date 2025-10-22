package com.example.demo.adapters.out.persistence.jpa.repositories;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.adapters.out.persistence.jpa.entities.RoleEntity;
import com.example.demo.adapters.out.persistence.jpa.entities.UserEntity;
import com.example.demo.adapters.out.persistence.jpa.interfaces.RoleRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.interfaces.UserRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.mappers.UserMapper;
import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.out.UserRepositoryPort;

import jakarta.transaction.Transactional;

@Repository
public class UserRepository implements UserRepositoryPort {

    private final UserRepositoryJpa userRepositoryJpa;
    private final RoleRepositoryJpa roleRepositoryJpa;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserRepository(UserRepositoryJpa userRepositoryJpa, RoleRepositoryJpa roleRepositoryJpa, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.roleRepositoryJpa = roleRepositoryJpa;
        this.passwordEncoder = passwordEncoder; 
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Iterable<UserEntity> userEntities = userRepositoryJpa.findAll();
        List<User> users = new ArrayList<>();
        userEntities.forEach(user -> users.add(userMapper.toDomain(user)));
        return users;
    }

    @Override
    @Transactional
    public void save(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        UserEntity userEntity = userMapper.toEntity(entity);
        Optional<RoleEntity> userRole = roleRepositoryJpa.findByName("ROLE_USER");
        List<RoleEntity> roles = new ArrayList<>();
        userRole.ifPresent(roles::add);
        userEntity.setRoles(roles);
        userRepositoryJpa.save(userEntity);
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userRepositoryJpa.findById(id)
                .map(userMapper::toDomain)
                .orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = userRepositoryJpa.findByEmail(email);
        if (userEntity != null) {
            return userMapper.toDomain(userEntity);
        }
        return null;
    }
}
