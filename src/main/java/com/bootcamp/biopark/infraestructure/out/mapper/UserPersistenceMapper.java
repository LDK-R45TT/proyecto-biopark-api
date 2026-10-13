package com.bootcamp.biopark.infraestructure.out.mapper;


import com.bootcamp.biopark.domain.model.User;
import com.bootcamp.biopark.infraestructure.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPersistenceMapper {

    public UserEntity toEntity(User user){
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getCreatedAt()
        );
    }

    public User toModel(UserEntity entity){
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole(),
                entity.getCreatedAt()
        );

    }

    public List<User> toModelList(List<UserEntity> entityList) {
        return entityList.stream()
                .map(this::toModel) // Pasas la lambda directamente aquí
                .toList();
    }
}
