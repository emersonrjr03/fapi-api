package com.herms.model;

import com.herms.entity.ResourceAttributeEntity;
import com.herms.enums.Condition;
import io.vertx.core.json.JsonObject;

import javax.persistence.*;
import java.util.Map;

public class AttributeValidation {

    private Long id;
    private Long    resourceAttributeId;
    private String condition;
    private String valueToCompare;
    private Map<String, Object> responseOnError;
    private Integer statusOnError;

    public AttributeValidation() {
    }

    public AttributeValidation(Long id, Long resourceAttributeId, String condition, String valueToCompare, Map<String, Object> responseOnError, Integer statusOnError) {
        this.id = id;
        this.resourceAttributeId = resourceAttributeId;
        this.condition = condition;
        this.valueToCompare = valueToCompare;
        this.responseOnError = responseOnError;
        this.statusOnError = statusOnError;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceAttributeId() {
        return resourceAttributeId;
    }

    public void setResourceAttributeId(Long resourceAttributeId) {
        this.resourceAttributeId = resourceAttributeId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getValueToCompare() {
        return valueToCompare;
    }

    public void setValueToCompare(String valueToCompare) {
        this.valueToCompare = valueToCompare;
    }

    public Map<String, Object> getResponseOnError() {
        return this.responseOnError;
    }

    public void setResponseOnError(Map<String, Object> responseOnError) {
        this.responseOnError = responseOnError;
    }

    public Integer getStatusOnError() {
        return statusOnError;
    }

    public void setStatusOnError(Integer statusOnError) {
        this.statusOnError = statusOnError;
    }
}
