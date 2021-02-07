package com.herms.mapper;

import com.herms.entity.UserEntity;
import com.herms.model.User;
import com.herms.model.UserRegistration;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static List<User> toModel(List<UserEntity> entityList){
        return entityList.stream().map(UserMapper::toModel).collect(Collectors.toList());
    }

    public static List<UserEntity> fromModel(List<User> modelList){
        return modelList.stream().map(UserMapper::fromModel).collect(Collectors.toList());
    }

    public static User toModel(UserEntity entity){
        User model = new User(
                entity.getId(),
                entity.getEmail());

        return model;
    }
    public static UserEntity fromModel(User model){
        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setEmail(model.getEmail());
        entity.setApiSecret(model.getApiSecret());
        return entity;
    }

    public static UserEntity fromModel(UserRegistration model){
        UserEntity entity = new UserEntity();
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());
        return entity;
    }
}
