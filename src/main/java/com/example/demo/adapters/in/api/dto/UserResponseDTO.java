package com.example.demo.adapters.in.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    @NotBlank
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String fullName;

    public UserResponseDTO(Long id, String email, String name, String lastName) {
        this.id = id;
        this.email = email;
        this.fullName = name + " " + lastName;
    }
}
