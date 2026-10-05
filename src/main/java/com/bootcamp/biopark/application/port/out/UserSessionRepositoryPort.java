package com.bootcamp.biopark.application.port.out;

import com.bootcamp.biopark.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserSessionRepositoryPort {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User save(User user);
    List<User> findAll();
    List<User> findByNameIgnoreCase(String name);
}
