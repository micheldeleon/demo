package com.example.demo.adapters.in.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adapters.in.api.dto.UserRegisterDto;
import com.example.demo.adapters.in.api.dto.UserResponseDTO;
import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.in.ListUsersPort;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final ListUsersPort listUsersPort;

    public UserController(ListUsersPort listUsersPort) {
        this.listUsersPort = listUsersPort;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = listUsersPort.listUsers();
        return users.stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getEmail()))
                .collect(Collectors.toList());
    }// mover mapeo

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto entity) {
        
        return ResponseEntity.ok(entity);
    }

}
