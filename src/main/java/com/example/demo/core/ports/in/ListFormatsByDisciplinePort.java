package com.example.demo.core.ports.in;

import java.util.List;

import com.example.demo.core.domain.models.Format;

public interface ListFormatsByDisciplinePort {
    List<Format> listByDisciplineId(Long disciplineId);

}
