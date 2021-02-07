package com.herms.mapper;

import com.herms.entity.ResourceAttributeEntity;
import com.herms.entity.ResourceEntity;
import com.herms.model.Resource;
import com.herms.model.ResourceAttribute;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceAttributeMapper {

    public static List<ResourceAttribute> toModel(List<ResourceAttributeEntity> entityList){
        return entityList.stream().map(ResourceAttributeMapper::toModel).collect(Collectors.toList());
    }

    public static List<ResourceAttributeEntity> fromModel(List<ResourceAttribute> modelList) {
        return modelList.stream().map(ResourceAttributeMapper::fromModel).collect(Collectors.toList());
    }

    public static ResourceAttribute toModel(ResourceAttributeEntity entity){
        ResourceAttribute model = new ResourceAttribute(
                entity.getId(),
                entity.getResource().getId(),
                entity.getFieldName(),
                entity.getFieldType(),
                entity.getFieldFormat());

        return model;
    }
    public static ResourceAttributeEntity fromModel(ResourceAttribute model){
        ResourceAttributeEntity entity = new ResourceAttributeEntity(
                model.getId(),
                new ResourceEntity(model.getResourceId()),
                model.getFieldName(),
                model.getFieldType(),
                model.getFieldFormat());
        return entity;
    }
}
