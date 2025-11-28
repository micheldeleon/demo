package com.example.demo.adapters.in.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adapters.in.api.dto.UserFullDto;
import com.example.demo.adapters.in.api.dto.UserRegisterDto;
import com.example.demo.adapters.in.api.dto.UserResponseDTO;
import com.example.demo.adapters.in.api.mappers.UserMapperDtos;
import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.User;
import com.example.demo.core.ports.in.GetUserPort;
import com.example.demo.core.ports.in.ListUsersPort;
import com.example.demo.core.ports.in.RegisterUserPort;
import com.example.demo.core.ports.in.UpdateProfilePort;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final ListUsersPort listUsersPort;
    private final RegisterUserPort registerUserPort;
    private final UpdateProfilePort updateProfilePort;
    private final GetUserPort getUserPort;

    public UserController(ListUsersPort listUsersPort, RegisterUserPort registerUserPort,
            UpdateProfilePort updateProfilePort, GetUserPort getUserPort) {
        this.listUsersPort = listUsersPort;
        this.registerUserPort = registerUserPort;
        this.updateProfilePort = updateProfilePort;
        this.getUserPort = getUserPort;
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
        try {
            registerUserPort.registerUser(UserMapperDtos.toDomain(entity));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/profile")
    public ResponseEntity<?> completeProfile(@Valid @RequestBody UserFullDto entity) {
        try {
            updateProfilePort.completion(UserMapperDtos.toDomain(entity));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping(params = {"id", "email"})
    public ResponseEntity<?> getUser(
            @RequestParam Long id,
            @RequestParam String email) {
        try {
            User user = getUserPort.getUserByIdAndEmail(id, email);
            return ResponseEntity.ok(UserMapperDtos.toFullDto(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(params = {"id", "email"})
    public ResponseEntity<?> getTournamentsByUserIdandEmail(
            @RequestParam Long id,
            @RequestParam String Email) {
        try {
            List<Tournament> tournaments = this.getUserPort.getUserByIdAndEmail(id, Email).getTournaments();
            return ResponseEntity.ok(tournaments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // @GetMapping(params = {"id", "email"})
    // public ResponseEntity<?> getTournamentsByUserIdandEmail(
    //         @RequestParam Long id,
    //         @RequestParam String Email) {
    //     try {
    //         List<Tournament> tournaments = this.getUserPort.getUserByIdAndEmail(id, Email).getTournaments();
    //         return ResponseEntity.ok(tournaments);
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }
}
