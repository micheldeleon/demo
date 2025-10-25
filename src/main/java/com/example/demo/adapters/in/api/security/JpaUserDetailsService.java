package com.example.demo.adapters.in.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.adapters.out.persistence.jpa.entities.UserEntity;
import com.example.demo.adapters.out.persistence.jpa.interfaces.UserRepositoryJpa;

public class JpaUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepositoryJpa repositoryJpa;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = (Optional<UserEntity>)repositoryJpa.findByEmail(username)
    }

}
