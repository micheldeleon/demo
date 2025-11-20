package com.example.demo.core.ports.out;

import com.example.demo.core.domain.models.Format;
import java.util.List;

public interface FormatRepositoryPort extends RepositoryPort<Format, Long>{
    List<Format> findByDisciplineId(Long disciplineId);
}
