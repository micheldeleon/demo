package com.example.demo.core.application.usecase;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.domain.models.TournamentStatus;
import com.example.demo.core.ports.in.ListTournamentsByStatusPort;
import com.example.demo.core.ports.out.FindTournamentsByStatusPort;

@Service
public class ListTournamentsByStatusUseCase implements ListTournamentsByStatusPort {

    private final FindTournamentsByStatusPort findTournamentsByStatusPort;

    public ListTournamentsByStatusUseCase(FindTournamentsByStatusPort findTournamentsByStatusPort) {
        this.findTournamentsByStatusPort = findTournamentsByStatusPort;
    }

    @Override
    public List<Tournament> listByStatus(TournamentStatus status) {
        Objects.requireNonNull(status, "status es requerido");
        return findTournamentsByStatusPort.findByStatus(status);
    }
}
