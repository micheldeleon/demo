package com.example.demo.adapters.in.api.mappers;

import com.example.demo.adapters.in.api.dto.UserRegisterDto;
import com.example.demo.adapters.in.api.dto.UserResponseDTO;
import com.example.demo.core.domain.models.User;

public class UserMapperDtos {

    public static User toDomain(UserRegisterDto dto) {
        return new User(null, dto.getName(), dto.getLastName(), dto.getEmail(), dto.getPassword());
    }

    public static UserResponseDTO toResponseDto(User user) {
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getName(), user.getLastName());
    }
}
