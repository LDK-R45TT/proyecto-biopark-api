package com.bootcamp.biopark.infraestructure.out.adapter;

import com.bootcamp.biopark.application.port.out.UserSessionRepositoryPort;
import com.bootcamp.biopark.domain.model.User;
import com.bootcamp.biopark.infraestructure.entity.UserEntity;
import com.bootcamp.biopark.infraestructure.out.mapper.UserPersistenceMapper;
import com.bootcamp.biopark.infraestructure.out.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserSessionAdapter implements UserSessionRepositoryPort {

    private final UserJpaRepository repository;
    private final UserPersistenceMapper mapper;

    public UserSessionAdapter(UserJpaRepository repository, UserPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toModel);

    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = repository.save(mapper.toEntity(user));
        return mapper.toModel(userEntity);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map( mapper::toModel).toList();
    }

    @Override
    public List<User> findByNameIgnoreCase(String name) {
        return mapper.toModelList(repository.findByNameIgnoreCase(name));
    }
}
