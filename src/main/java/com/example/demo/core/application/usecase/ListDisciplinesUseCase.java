package com.example.demo.core.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.core.domain.models.Discipline;
import com.example.demo.core.ports.in.ListDisciplinesPort;
import com.example.demo.core.ports.out.DisciplineRepositoryPort;

@Service
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
