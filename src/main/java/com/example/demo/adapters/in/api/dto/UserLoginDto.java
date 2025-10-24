package com.example.demo.adapters.in.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDto {
    @NotBlank
    String email;
    @NotBlank
    String password;
}
