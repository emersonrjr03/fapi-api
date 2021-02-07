package com.herms.repository;

import com.herms.entity.ResourceEntity;
import com.herms.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {

    public UserEntity getByApiSecret(String apiSecret) {
        return list("apiSecret = ?1", apiSecret).stream().findFirst().orElse(null);
    }
}
