package com.herms.entity;

import com.herms.converter.FieldTypeConverter;
import com.herms.enums.FieldType;
import com.herms.model.Resource;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RESOURCE_ATTRIBUTE")
public class ResourceAttributeEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private ResourceEntity resource;
    private String fieldName;
    @Convert(converter = FieldTypeConverter.class)
    private FieldType fieldType;
    private String fieldFormat;


    public ResourceAttributeEntity() {
    }

    public ResourceAttributeEntity(Long id, ResourceEntity resource, String fieldName, FieldType fieldType, String fieldFormat) {
        this.id = id;
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldFormat = fieldFormat;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldFormat() {
        return fieldFormat;
    }

    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = fieldFormat;
    }
}
