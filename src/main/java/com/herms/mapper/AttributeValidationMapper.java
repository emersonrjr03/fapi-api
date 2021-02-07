package com.herms.mapper;

import com.herms.entity.AttributeValidationEntity;
import com.herms.entity.ResourceAttributeEntity;
import com.herms.entity.ResourceEntity;
import com.herms.enums.Condition;
import com.herms.model.AttributeValidation;
import com.herms.model.Resource;
import com.herms.model.ResourceAttribute;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AttributeValidationMapper {

    public static List<AttributeValidation> toModel(List<AttributeValidationEntity> entityList){
        return entityList.stream().map(AttributeValidationMapper::toModel).collect(Collectors.toList());
    }

    public static List<AttributeValidationEntity> fromModel(List<AttributeValidation> modelList){
        return modelList.stream().map(AttributeValidationMapper::fromModel).collect(Collectors.toList());
    }

    public static AttributeValidation toModel(AttributeValidationEntity entity){
        AttributeValidation model = new AttributeValidation(
                entity.getId(),
                entity.getResourceAttribute().getId(),
                entity.getCondition().getCode(),
                entity.getValueToCompare(),
                entity.getResponseOnError(),
                entity.getStatusOnError());

        return model;
    }
    public static AttributeValidationEntity fromModel(AttributeValidation model){
        AttributeValidationEntity entity = new AttributeValidationEntity(
                model.getId(),
                new ResourceAttributeEntity(model.getResourceAttributeId()),
                Condition.toEnum(model.getCondition()),
                model.getValueToCompare(),
                model.getResponseOnError(),
                model.getStatusOnError());
        return entity;
    }
}
