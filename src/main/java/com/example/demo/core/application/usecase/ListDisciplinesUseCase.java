package com.example.demo.core.application.usecase;

import java.util.List;

import com.example.demo.core.domain.models.Discipline;
import com.example.demo.core.ports.in.ListDisciplinesPort;
import com.example.demo.core.ports.out.DisciplineRepositoryPort;


public class ListDisciplinesUseCase implements ListDisciplinesPort {

    private final DisciplineRepositoryPort disciplineRepositoryPort;

    public ListDisciplinesUseCase(DisciplineRepositoryPort disciplineRepositoryPort) {
        this.disciplineRepositoryPort = disciplineRepositoryPort;
    }

    @Override
    public List<Discipline> listAll() {
        return disciplineRepositoryPort.findAll();
    }
}
