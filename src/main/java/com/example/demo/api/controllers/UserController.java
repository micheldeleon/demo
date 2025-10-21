package com.example.demo.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.domain.models.User;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final ListUsersPort listUsersPort;

    public UserController(ListUsersPort listUsersPort) {
        this.listUsersPort = listUsersPort;
    }

    @GetMapping
    @RequestMapping("/all")
    public List<User> getAllUsers() {
        return listUsersPort.listUsers();
    }

}
