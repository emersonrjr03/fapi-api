package com.herms.entity;

import com.herms.converter.BooleanToStringConverter;
import com.herms.converter.FieldTypeConverter;
import com.herms.enums.FieldType;

import javax.persistence.*;
import java.util.List;

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
    @Convert(converter= BooleanToStringConverter.class)
    @Column(name="IS_PRIMARY_KEY", length=1)
    private Boolean fieldIsPk;
    @OneToMany(mappedBy = "resourceAttribute")
    private List<AttributeValidationEntity> validationList;


    public ResourceAttributeEntity() {
    }

    public ResourceAttributeEntity(Long id) {
        this.id = id;
    }

    public ResourceAttributeEntity(Long id, ResourceEntity resource, String fieldName, FieldType fieldType, String fieldFormat, Boolean fieldIsPk) {
        this(id);
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldFormat = fieldFormat;
        this.fieldIsPk = fieldIsPk;

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

    public Boolean getFieldIsPk() {
        return fieldIsPk;
    }

    public void setFieldIsPk(Boolean fieldIsPk) {
        this.fieldIsPk = fieldIsPk;
    }
}
