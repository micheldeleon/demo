package com.example.demo.adapters.in.api.dto;

import jakarta.validation.constraints.NotBlank;

public record RunnerRegistrationRequest(
        String fullName,
        String nationalId) {
}
