package com.herms.mapper;

import com.herms.entity.ResourceRowEntity;
import com.herms.entity.ResourceEntity;
import com.herms.model.ResourceRow;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceRowMapper {
    public static List<ResourceRow> toModel(List<ResourceRowEntity> entityList){
        return entityList.stream().map(ResourceRowMapper::toModel).collect(Collectors.toList());
    }

    public static List<ResourceRowEntity> fromModel(List<ResourceRow> modelList){
        return modelList.stream().map(ResourceRowMapper::fromModel).collect(Collectors.toList());
    }

    public static ResourceRow toModel(ResourceRowEntity entity){
        ResourceRow model = new ResourceRow(
                                            entity.getId(),
                                            entity.getResource().getId(),
                                            ResourceRowValueMapper.toModel(entity.getValueList()));

        return model;
    }
    public static ResourceRowEntity fromModel(ResourceRow model){
        ResourceRowEntity entity = new ResourceRowEntity(
                model.getId(),
                new ResourceEntity(model.getResourceId()),
                ResourceRowValueMapper.fromModel(model.getValueList()));
        return entity;
    }
}
