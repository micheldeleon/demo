package com.example.demo.adapters.out.persistence.jpa.mappers;

import com.example.demo.adapters.out.persistence.jpa.entities.FormatEntity;
import com.example.demo.core.domain.models.Format;
import com.example.demo.core.domain.models.SimpleFormat;

public class FormatMapper {

    private FormatMapper() {
    }

    public static Format toDomain(FormatEntity entity) {
        if (entity == null) {
            return null;
        }
        return new SimpleFormat(
                entity.getId(),
                entity.getName(),
                entity.isGeneraFixture());
    }

    public static FormatEntity toEntity(Format format) {
        if (format == null) {
            return null;
        }
        FormatEntity entity = new FormatEntity();
        entity.setId(format.getId());
        entity.setName(format.getName());
        entity.setGeneraFixture(format.isGeneraFixture());
        return entity;
    }
}
