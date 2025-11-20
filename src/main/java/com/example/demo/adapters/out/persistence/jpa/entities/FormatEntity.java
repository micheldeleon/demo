package com.example.demo.adapters.out.persistence.jpa.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Formats")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FormatEntity {
    @Id
    private Long id;
    private String name;
    private boolean generaFixture;
}
