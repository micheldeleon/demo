package com.example.demo.adapters.in.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adapters.in.api.dto.UserLoginDto;
import com.example.demo.adapters.in.api.dto.UserRegisterDto;
import com.example.demo.adapters.in.api.dto.UserResponseDTO;
import com.example.demo.adapters.in.api.mappers.UserMapperDtos;
import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.in.ListUsersPort;
import com.example.demo.core.ports.in.LoginUserPort;
import com.example.demo.core.ports.in.RegisterUserPort;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final ListUsersPort listUsersPort;
    private final RegisterUserPort registerUserPort;
    private final LoginUserPort loginUserPort;

    public UserController(ListUsersPort listUsersPort, RegisterUserPort registerUserPort, LoginUserPort loginUserPort) {
        this.listUsersPort = listUsersPort;
        this.registerUserPort = registerUserPort;
        this.loginUserPort = loginUserPort;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = listUsersPort.listUsers();
        return users.stream()
                .map(UserMapperDtos::toResponseDto)
                .collect(Collectors.toList());
    }// mover mapeo

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto entity) {
        try{
            registerUserPort.registerUser(UserMapperDtos.toDomain(entity));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    @GetMapping("/login")
    public String getMethodName(@Valid@RequestBody UserLoginDto userLoginDto) {
        try{
            loginUserPort.loginUser(userLoginDto.getEmail(), userLoginDto.getPassword());
            return "Login successful for user: " + userLoginDto.getEmail();
        } catch (Exception e) {
            return "Login failed: " + e.getMessage();
        }
    }
    

}
