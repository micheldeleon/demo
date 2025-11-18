package com.example.demo.adapters.in.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adapters.in.api.dto.DisciplineResponse;
import com.example.demo.adapters.in.api.mappers.DisciplineDtoMapper;
import com.example.demo.core.ports.in.ListDisciplinesPort;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    private final ListDisciplinesPort listDisciplinesPort;

    public DisciplineController(ListDisciplinesPort listDisciplinesPort) {
        this.listDisciplinesPort = listDisciplinesPort;
    }

    @GetMapping
    public List<DisciplineResponse> list() {
        return listDisciplinesPort.listAll()
                .stream()
                .map(DisciplineDtoMapper::toResponse)
                .toList();
    }
}
