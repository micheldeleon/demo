package com.example.demo.core.ports.in;

import java.util.List;

import com.example.demo.core.domain.models.User;

public interface ListUsersPort {
    List<User> listUsers();
}
