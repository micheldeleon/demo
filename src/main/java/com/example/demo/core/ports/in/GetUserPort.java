package com.example.demo.core.ports.in;

import com.example.demo.core.domain.models.User;

public interface GetUserPort {
    User getUserByIdAndEmail(Long id, String Email);
}
