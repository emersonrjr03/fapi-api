package com.herms.service;

import com.herms.entity.ResourceAttributeEntity;
import com.herms.mapper.ResourceAttributeMapper;
import com.herms.model.ResourceAttribute;
import com.herms.model.ResourceRow;
import com.herms.pages.PageRequest;
import com.herms.repository.ResourceAttributeRepository;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ResourceAttributeService {
    @Inject
    private ResourceAttributeRepository repository;
    @Inject
    private ResourceRowValueService resourceRowValueService;

    public Response getAllPaged(PageRequest pageRequest){
        List<ResourceAttributeEntity> entityList = repository.findAll().page(Page.of(pageRequest.getPageNumber(), pageRequest.getPageSize())).list();
        return Response
                .ok(entityList.stream().map(ResourceAttributeMapper::toModel).collect(Collectors.toList()))
                .build();
    }

    public Response getById(Long id) {
        ResourceAttributeEntity entity = repository.findById(id);
        if(entity == null){
            throw new EntityNotFoundException("Resource Attribute not found! ID: " + id);
        }
        return Response
                .ok(ResourceAttributeMapper.toModel(repository.findById(id)))
                .build();
    }

    public Response create(ResourceAttribute model) {

        ResourceAttributeEntity entity = ResourceAttributeMapper.fromModel(model);
        repository.persist(entity);
        return Response.ok(ResourceAttributeMapper.toModel(entity)).status(Response.Status.CREATED).build();
    }

    public Response update(Long id, ResourceAttribute model) {

        ResourceAttributeEntity entity = repository.findById(id);
        if (entity == null)
            throw new WebApplicationException("Resource Attribute not found!", Response.Status.NOT_FOUND);

        ResourceAttributeEntity modifications = ResourceAttributeMapper.fromModel(model);



        resourceRowValueService.updateFieldValuesIfNeeded(entity, modifications);

        entity.setFieldName(modifications.getFieldName());
        entity.setFieldFormat(modifications.getFieldFormat());
        entity.setFieldType(modifications.getFieldType());


        return Response.ok(ResourceAttributeMapper.toModel(entity)).build();
    }

    public Response delete(Long id) {
        if (repository.findById(id) == null)
            throw new WebApplicationException("Resource Attribute not found!", Response.Status.NOT_FOUND);

        repository.deleteById(id);
        return Response.noContent().build();
    }
}
