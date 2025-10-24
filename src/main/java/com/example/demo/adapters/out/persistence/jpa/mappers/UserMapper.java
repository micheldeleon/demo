package com.example.demo.adapters.out.persistence.jpa.mappers;

import com.example.demo.adapters.out.persistence.jpa.entities.UserEntity;
import com.example.demo.core.domain.models.User;

public class UserMapper {
    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setNationalId(user.getNationalId());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        return userEntity;
    }

    public User toDomain(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setDateOfBirth(userEntity.getDateOfBirth());
        user.setNationalId(userEntity.getNationalId());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        return user;
    }

}
