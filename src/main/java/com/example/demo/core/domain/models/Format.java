package com.example.demo.core.domain.models;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class Format {
    private String name;
    private boolean generaFixture;
}
