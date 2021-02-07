package com.herms.mapper;

import com.herms.entity.ResourceAttributeEntity;
import com.herms.entity.ResourceEntity;
import com.herms.model.Resource;
import com.herms.model.ResourceAttribute;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResourceAttributeMapper {

    public static Map<String, ResourceAttribute> toModel(Map<String, ResourceAttributeEntity> entityMap){
        return entityMap.values().stream().map(ResourceAttributeMapper::toModel).collect(Collectors.toMap(model -> model.getFieldName(), model -> model));
    }

    public static Map<String, ResourceAttributeEntity> fromModel(Map<String, ResourceAttribute> modelMap) {
        return modelMap.values().stream().map(ResourceAttributeMapper::fromModel).collect(Collectors.toMap(entity -> entity.getFieldName(), entity -> entity));
    }

    public static ResourceAttribute toModel(ResourceAttributeEntity entity){
        ResourceAttribute model = new ResourceAttribute(
                entity.getId(),
                entity.getResource().getId(),
                entity.getFieldName(),
                entity.getFieldType(),
                entity.getFieldFormat(),
                entity.getFieldIsPk());

        return model;
    }
    public static ResourceAttributeEntity fromModel(ResourceAttribute model){
        ResourceAttributeEntity entity = new ResourceAttributeEntity(
                model.getId(),
                new ResourceEntity(model.getResourceId()),
                model.getFieldName(),
                model.getFieldType(),
                model.getFieldFormat(),
                model.getFieldIsPk());
        return entity;
    }
}
