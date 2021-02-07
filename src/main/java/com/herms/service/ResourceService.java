package com.herms.service;

import com.herms.entity.ResourceAttributeEntity;
import com.herms.entity.ResourceEntity;
import com.herms.mapper.ResourceMapper;
import com.herms.model.Resource;
import com.herms.pages.PageRequest;
import com.herms.repository.ResourceRepository;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BeanParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ResourceService {
    @Inject
    ResourceRepository repository;

    public Response getAllPaged(PageRequest pageRequest){
        List<ResourceEntity> entityList = repository.findAll().page(Page.of(pageRequest.getPageNumber(), pageRequest.getPageSize())).list();
        return Response
                .ok(entityList.stream().map(ResourceMapper::toModel).collect(Collectors.toList()))
                .build();
    }

    public Response getById(Long id) {
        ResourceEntity entity = repository.findById(id);
        if(entity == null){
            throw new EntityNotFoundException("Resource not found! ID: " + id);
        }

        return Response
                .ok(ResourceMapper.toModel(repository.findById(id)))
                .build();
    }

    public Response create(Resource model) {
        ResourceEntity entity = ResourceMapper.fromModel(model);
        repository.persist(entity);

        return Response.ok(ResourceMapper.toModel(entity)).status(Response.Status.CREATED).build();
    }

    public Response update(Long id, Resource model) {

        ResourceEntity entity = repository.findById(id);
        if (entity == null)
            throw new WebApplicationException("Resource not found!", Response.Status.NOT_FOUND);

        ResourceEntity modifications = ResourceMapper.fromModel(model);
        entity.setName(model.getName());
        entity.setAttributesList(modifications.getAttributesList());
        return Response.ok(ResourceMapper.toModel(entity)).build();
    }

    public Response delete(Long id) {
        if (repository.findById(id) == null)
            throw new WebApplicationException("Resource not found!", Response.Status.NOT_FOUND);

        repository.deleteById(id);
        return Response.noContent().build();
    }

    public Resource getByUserIdAndResourceName(Long userId, String resourceName) {
        ResourceEntity entity = repository.list("createdBy.id = ?1 and lower(name) = ?2", userId, (resourceName + "").toLowerCase()).stream().findFirst().orElse(null);
        return entity != null ? ResourceMapper.toModel(entity) : null;
    }
}
