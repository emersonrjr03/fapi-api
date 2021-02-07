package com.herms.model;

import com.herms.enums.FieldType;

public class ResourceAttribute {
    private Long id;
    private Long resourceId;
    private String fieldName;
    private FieldType fieldType;
    private String fieldFormat;
    private Boolean fieldIsPk;

    public ResourceAttribute() {
    }

    public ResourceAttribute(Long id, Long resourceId, String fieldName, FieldType fieldType, String fieldFormat, Boolean fieldIsPk) {
        this.id = id;
        this.resourceId = resourceId;
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

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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

    public void setFieldIsPk(Boolean PK) {
        fieldIsPk = PK;
    }
}
