package com.example.demo.adapters.in.api.mappers;

import com.example.demo.adapters.in.api.dto.FormatResponse;
import com.example.demo.core.domain.models.Format;

public class FormatDtoMapper {

    private FormatDtoMapper() {
    }

    public static FormatResponse toResponse(Format format) {
        if (format == null) {
            return null;
        }
        return new FormatResponse(format.getId(), format.getName(), format.isGeneraFixture());
    }
}
