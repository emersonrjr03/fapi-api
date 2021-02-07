package com.herms.service;

import com.herms.entity.ResourceEntity;
import com.herms.entity.UserEntity;
import com.herms.mapper.ResourceMapper;
import com.herms.mapper.UserMapper;
import com.herms.model.Resource;
import com.herms.model.User;
import com.herms.model.UserRegistration;
import com.herms.pages.PageRequest;
import com.herms.repository.ResourceRepository;
import com.herms.repository.UserRepository;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.security.KeyPairGenerator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {
    @Inject
    private UserRepository repository;
    @Inject
    private AuthService authService;

    public Response getAllPaged(PageRequest pageRequest){
        List<UserEntity> entityList = repository.findAll().page(Page.of(pageRequest.getPageNumber(), pageRequest.getPageSize())).list();
        return Response
                .ok(entityList.stream().map(UserMapper::toModel).collect(Collectors.toList()))
                .build();
    }

    public Response getById(Long id) {
        UserEntity entity = repository.findById(id);
        if(entity == null){
            throw new EntityNotFoundException("User not found! ID: " + id);
        }

        return Response
                .ok(UserMapper.toModel(repository.findById(id)))
                .build();
    }

    public Response create(UserRegistration model) {
        UserEntity entity = UserMapper.fromModel(model);
        entity.setApiSecret(authService.generateNewApiSecret());

        repository.persist(entity);
        return Response.ok(UserMapper.toModel(entity)).status(Response.Status.CREATED).build();
    }

    public Response update(Long id, User model) {

        UserEntity entity = repository.findById(id);
        if (entity == null)
            throw new WebApplicationException("User not found!", Response.Status.NOT_FOUND);

        UserEntity modifications = UserMapper.fromModel(model);

        entity.setEmail(modifications.getEmail());

        return Response.ok(UserMapper.toModel(entity)).build();
    }

    public Response delete(Long id) {
        if (repository.findById(id) == null)
            throw new WebApplicationException("User not found!", Response.Status.NOT_FOUND);

        repository.deleteById(id);
        return Response.noContent().build();
    }


    public User getByApiSecret(String apiSecret) {
        UserEntity entity = repository.getByApiSecret(apiSecret);
        return entity != null ? UserMapper.toModel(entity):  null;
    }
}
