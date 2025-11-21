package com.example.demo.core.application.usecase;

import java.util.List;

import com.example.demo.core.domain.models.Format;
import com.example.demo.core.ports.in.ListFormatsByDisciplinePort;
import com.example.demo.core.ports.out.FormatRepositoryPort;

public class ListFormatsByDisciplineUseCase implements ListFormatsByDisciplinePort {

    private final FormatRepositoryPort formatRepositoryPort;

    public ListFormatsByDisciplineUseCase(FormatRepositoryPort formatRepositoryPort) {
        this.formatRepositoryPort = formatRepositoryPort;
    }

    //Le pasamos un id de una disciplina y nos devuelve los formatos asociados
    @Override
    public List<Format> listByDisciplineId(Long disciplineId) {
        return formatRepositoryPort.findByDisciplineId(disciplineId);
    }

}
