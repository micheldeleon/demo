package com.example.demo.adapters.in.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adapters.in.api.dto.DisciplineResponse;
import com.example.demo.adapters.in.api.dto.FormatResponse;
import com.example.demo.adapters.in.api.mappers.DisciplineDtoMapper;
import com.example.demo.adapters.in.api.mappers.FormatDtoMapper;
import com.example.demo.core.ports.in.ListDisciplinesPort;
import com.example.demo.core.ports.in.ListFormatsByDisciplinePort;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    private final ListDisciplinesPort listDisciplinesPort;
    private final ListFormatsByDisciplinePort listFormatsByDisciplinePort;

    public DisciplineController(ListDisciplinesPort listDisciplinesPort,
            ListFormatsByDisciplinePort listFormatsByDisciplinePort) {
        this.listDisciplinesPort = listDisciplinesPort;
        this.listFormatsByDisciplinePort = listFormatsByDisciplinePort;
    }

    @GetMapping
    public List<DisciplineResponse> list() {
        return listDisciplinesPort.listAll()
                .stream()
                .map(DisciplineDtoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{disciplineId}/formats")
    public List<FormatResponse> listFormats(@PathVariable Long disciplineId) {
        return listFormatsByDisciplinePort.listByDisciplineId(disciplineId)
                .stream()
                .map(FormatDtoMapper::toResponse)
                .toList();
    }
}
