package com.example.demo.core.domain.models;

import java.util.Date;

import com.example.demo.core.domain.services.ValidateUruguayanIdService;
import com.example.demo.core.domain.services.ValidateUserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name; // nombre
    private String lastName; // apellido
    private String email;
    private String password;
    private Date dateOfBirth; // fechaNacimiento
    private String nationalId; // ci
    private String phoneNumber; // celular

    public User(Long id, String name, String lastName, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        ValidateUserService.validate(this);
    }
}
