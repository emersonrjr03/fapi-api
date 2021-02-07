package com.herms.mapper;

import com.herms.entity.ResourceEntity;
import com.herms.entity.UserEntity;
import com.herms.model.Resource;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceMapper {
    public static List<Resource> toModel(List<ResourceEntity> entityList){
        return entityList.stream().map(ResourceMapper::toModel).collect(Collectors.toList());
    }

    public static List<ResourceEntity> fromModel(List<Resource> modelList){
        return modelList.stream().map(ResourceMapper::fromModel).collect(Collectors.toList());
    }

    public static Resource toModel(ResourceEntity entity){
        Resource model = new Resource(entity.getId(),
                entity.getName(),
                ResourceAttributeMapper.toModel(entity.getAttributesList()),
                UserMapper.toModel(entity.getCreatedBy()));

        return model;
    }
    public static ResourceEntity fromModel(Resource model){
        ResourceEntity entity = new ResourceEntity(model.getId(),
                model.getName(),
                ResourceAttributeMapper.fromModel(model.getAttributesList()),
                UserMapper.fromModel(model.getCreatedBy()));
        return entity;
    }
}
