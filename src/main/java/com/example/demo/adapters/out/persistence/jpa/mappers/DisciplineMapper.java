package com.example.demo.adapters.out.persistence.jpa.mappers;

import com.example.demo.adapters.out.persistence.jpa.entities.DisciplineEntity;
import com.example.demo.core.domain.models.Discipline;

public class DisciplineMapper {

    private DisciplineMapper() {
    }

    public static Discipline toDomain(DisciplineEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Discipline(entity.getId(), entity.isCollective(), entity.getName(), null);
    }

    public static DisciplineEntity toEntity(Discipline discipline) {
        if (discipline == null) {
            return null;
        }
        return new DisciplineEntity(discipline.getId(), discipline.getName(), discipline.isCollective());
    }
}
