package com.example.demo.core.ports;

import java.util.List;

public interface RepositoryPort<T, ID> {
    void save(T entity);
    T findById(ID id);
    List<T> findAll();
}
