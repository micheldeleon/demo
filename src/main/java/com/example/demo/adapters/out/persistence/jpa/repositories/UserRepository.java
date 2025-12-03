package com.example.demo.adapters.out.persistence.jpa.repositories;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.adapters.out.persistence.jpa.entities.DepartmentEntity;
import com.example.demo.adapters.out.persistence.jpa.entities.RoleEntity;
import com.example.demo.adapters.out.persistence.jpa.entities.UserEntity;
import com.example.demo.adapters.out.persistence.jpa.interfaces.DepartmentRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.interfaces.RoleRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.interfaces.UserRepositoryJpa;
import com.example.demo.adapters.out.persistence.jpa.mappers.DepartmentMapper;
import com.example.demo.adapters.out.persistence.jpa.mappers.UserMapper;
import com.example.demo.config.ApplicationConfig;
import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.out.UserRepositoryPort;

import jakarta.transaction.Transactional;

@Repository
public class UserRepository implements UserRepositoryPort {

    private final ApplicationConfig applicationConfig;

    private final UserRepositoryJpa userRepositoryJpa;
    private final RoleRepositoryJpa roleRepositoryJpa;
    private final DepartmentRepositoryJpa departmentRepositoryJpa;
    private final PasswordEncoder passwordEncoder;

    public UserRepository(UserRepositoryJpa userRepositoryJpa, RoleRepositoryJpa roleRepositoryJpa,
            PasswordEncoder passwordEncoder, DepartmentRepositoryJpa dRepositoryJpa,
            ApplicationConfig applicationConfig) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.roleRepositoryJpa = roleRepositoryJpa;
        this.passwordEncoder = passwordEncoder;
        this.departmentRepositoryJpa = dRepositoryJpa;
        this.applicationConfig = applicationConfig;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Iterable<UserEntity> userEntities = userRepositoryJpa.findAll();
        List<User> users = new ArrayList<>();
        userEntities.forEach(user -> users.add(UserMapper.toDomain(user)));
        return users;
    }

    @Override
    @Transactional
    public void save(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        DepartmentEntity dep = departmentRepositoryJpa.findById(0L).get();
        entity.setDepartment(DepartmentMapper.toDomain(dep));
        UserEntity userEntity = UserMapper
                .toEntity(entity);
        Optional<RoleEntity> userRole = roleRepositoryJpa.findByName("ROLE_USER");
        List<RoleEntity> roles = new ArrayList<>();
        userRole.ifPresent(roles::add);
        userEntity.setRoles(roles);
        // departmentRepositoryJpa.findById(0);
        userRepositoryJpa.save(userEntity);
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return UserMapper.toDomain(userRepositoryJpa.findById(id).get());
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        return userRepositoryJpa.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    @Transactional
    public void update(User user) {
        User lastUser = findByEmail(user.getEmail()).get();
        lastUser.profileUpdate(user);
        userRepositoryJpa.save(UserMapper.toEntity(lastUser));
    }
}
