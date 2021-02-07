package com.herms.service;

import com.herms.entity.ResourceEntity;
import com.herms.entity.ResourceRowEntity;
import com.herms.entity.ResourceRowValueEntity;
import com.herms.mapper.ResourceAttributeMapper;
import com.herms.mapper.ResourceMapper;
import com.herms.mapper.ResourceRowMapper;
import com.herms.model.Resource;
import com.herms.model.ResourceRow;
import com.herms.model.ResourceRowValue;
import com.herms.pages.PageRequest;
import com.herms.repository.ResourceRowRepository;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class ResourceRowService {
    @Inject
    ResourceRowRepository repository;
    @Inject
    ResourceRowValueService resourceRowValueService;

    public Response getAllPaged(PageRequest pageRequest){
        List<ResourceRowEntity> entityList = repository.findAll().page(Page.of(pageRequest.getPageNumber(), pageRequest.getPageSize())).list();
        return Response
                .ok(entityList.stream().map(ResourceRowMapper::toModel).collect(Collectors.toList()))
                .build();
    }

    public Response getById(Long id) {
        ResourceRowEntity entity = repository.findById(id);
        if(entity == null){
            throw new EntityNotFoundException("Resource Row not found! ID: " + id);
        }
        return Response
                .ok(ResourceRowMapper.toModel(repository.findById(id)))
                .build();
    }

    public Response create(ResourceRow model) {
        List<ResourceRowValue> rowValueList = model.getValueList();
        model.setValueList(new ArrayList<>());

        ResourceRowEntity entity = ResourceRowMapper.fromModel(model);
        repository.persist(entity);
        model = ResourceRowMapper.toModel(entity);

        for(ResourceRowValue value : rowValueList) {
            value.setRowId(model.getId());
            model.getValueList().add(resourceRowValueService.create(value));
        }

        return Response.ok(model).status(Response.Status.CREATED).build();
    }

    public Response update(Long id, ResourceRow model) {

        ResourceRowEntity entity = repository.findById(id);
        if (entity == null)
            throw new WebApplicationException("ResourceData not found!", Response.Status.NOT_FOUND);

        ResourceRowEntity modifications = ResourceRowMapper.fromModel(model);
        entity.setValueList(modifications.getValueList());

        return Response.ok(ResourceRowMapper.toModel(entity)).build();
    }

    public Response delete(Long id) {
        if (repository.findById(id) == null)
            throw new WebApplicationException("ResourceData not found!", Response.Status.NOT_FOUND);

        repository.deleteById(id);
        return Response.noContent().build();
    }

    public List<Map<String, Object>> getRowsByResource(Resource resource) {
        List<ResourceRowEntity> rowList = repository.list("resource.id = ?1", resource.getId());
        List<Map<String, Object>> rows = new ArrayList<>();
        for(ResourceRowEntity row : rowList) {
            rows.add(resourceRowValueService.getRowValuesUsingResourceAttributes(row, ResourceAttributeMapper.fromModel(resource.getAttributesList())));
        }

        return rows;
    }
}
