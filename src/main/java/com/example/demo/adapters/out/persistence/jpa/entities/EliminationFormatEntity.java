package com.example.demo.adapters.out.persistence.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "elimination_formats")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EliminationFormatEntity extends FormatEntity {
    // Sin campos adicionales por ahora; se mantiene para soportar herencia TPT.
}
