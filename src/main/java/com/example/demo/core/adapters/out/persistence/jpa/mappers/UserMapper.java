package com.example.demo.core.adapters.out.persistence.jpa.mappers;

import com.example.demo.core.adapters.out.persistence.jpa.entities.UserEntity;
import com.example.demo.core.domain.models.User;

public class UserMapper {
    public UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName()); 
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }
    public User toDomain(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName()); 
        user.setEmail(userEntity.getEmail());
        return user;
    }
}
