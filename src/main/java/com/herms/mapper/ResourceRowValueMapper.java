package com.herms.mapper;

import com.herms.entity.ResourceRowEntity;
import com.herms.entity.ResourceRowValueEntity;
import com.herms.model.ResourceRowValue;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceRowValueMapper {
    public static List<ResourceRowValue> toModel(List<ResourceRowValueEntity> entityList){
        return entityList.stream().map(ResourceRowValueMapper::toModel).collect(Collectors.toList());
    }

    public static List<ResourceRowValueEntity> fromModel(List<ResourceRowValue> modelList){
        return modelList.stream().map(ResourceRowValueMapper::fromModel).collect(Collectors.toList());
    }

    public static ResourceRowValue toModel(ResourceRowValueEntity entity){
        ResourceRowValue model = new ResourceRowValue(
                                            entity.getId(),
                                            entity.getField(),
                                            entity.getValue(),
                entity.getRow() != null ? entity.getRow().getId() : null);

        return model;
    }
    public static ResourceRowValueEntity fromModel(ResourceRowValue model){
        ResourceRowValueEntity entity = new ResourceRowValueEntity(
                                                    model.getId(),
                                                    model.getField(),
                                                    model.getValue(),
                model.getRowId() != null ? new ResourceRowEntity(model.getRowId()) : null);
        return entity;
    }
}
