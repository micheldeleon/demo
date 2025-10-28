package com.example.demo.core.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NonUniqueFormat extends Format { // NoUnico
    private String notes;
}

