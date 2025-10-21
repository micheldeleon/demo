package com.example.demo.adapters.in.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adapters.in.api.dto.UserResponseDTO;
import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.in.ListUsersPort;


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
    }//mover mapeo 

}
