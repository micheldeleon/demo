package com.example.demo.adapters.in.api.mappers;

import com.example.demo.adapters.in.api.dto.DisciplineResponse;
import com.example.demo.core.domain.models.Discipline;

public class DisciplineDtoMapper {

    private DisciplineDtoMapper() {
    }

    public static DisciplineResponse toResponse(Discipline discipline) {
        if (discipline == null) {
            return null;
        }
        return new DisciplineResponse(discipline.getId(), discipline.getName(), discipline.isCollective());
    }
}
