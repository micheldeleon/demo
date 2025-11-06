package com.example.demo.adapters.in.api.mappers;

import com.example.demo.adapters.in.api.dto.UserFullDto;
import com.example.demo.adapters.in.api.dto.UserRegisterDto;
import com.example.demo.adapters.in.api.dto.UserResponseDTO;
import com.example.demo.core.domain.models.Department;
import com.example.demo.core.domain.models.User;

public class UserMapperDtos {

    public static User toDomain(UserRegisterDto dto) {
        return new User(null, dto.getName(), dto.getLastName(), dto.getEmail(), dto.getPassword());
    }

    public static UserResponseDTO toResponseDto(User user) {
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getName(), user.getLastName());
    }

    public static User toDomain(UserFullDto user) {
        return new User(user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getDateOfBirth(),
                user.getNationalId(),
                user.getPhoneNumber(),
                user.getAddress(),
                new Department(user.getDepartmentId()));
    }

    public static UserFullDto toFullDto(User user) {

        return new UserFullDto(user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getDateOfBirth(),
                user.getNationalId(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getDepartment().getId());
    }

}
