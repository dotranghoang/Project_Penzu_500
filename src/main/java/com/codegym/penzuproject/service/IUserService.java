package com.codegym.penzuproject.service;

import com.codegym.penzuproject.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    Optional<User> findById(Long id);
    void save(User user);
}
