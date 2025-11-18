package com.example.demo.core.application.usecase;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.core.domain.models.Format;

import com.example.demo.core.ports.in.ListFormatsByDisciplinePort;
import com.example.demo.core.ports.out.FormatRepositoryPort;

@Service
public class ListFormatsByDisciplineUseCase implements ListFormatsByDisciplinePort {
    // implementa el puerto de entrada creado.

    private final FormatRepositoryPort formatRepositoryPort;

    public ListFormatsByDisciplineUseCase(FormatRepositoryPort formatRepositoryPort) {
        this.formatRepositoryPort = formatRepositoryPort;
    }

    @Override
    public List<Format> listByDisciplineId(Long disciplineId) {
        return formatRepositoryPort.findByDisciplineId(disciplineId);
    }

}
