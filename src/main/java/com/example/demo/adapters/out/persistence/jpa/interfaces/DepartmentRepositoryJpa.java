package com.example.demo.adapters.out.persistence.jpa.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.adapters.out.persistence.jpa.entities.DepartmentEntity;

public interface DepartmentRepositoryJpa extends CrudRepository<DepartmentEntity,Long> {

}
