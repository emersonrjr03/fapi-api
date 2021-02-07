package com.herms.entity;

import com.herms.converter.ConditionConverter;
import com.herms.converter.StringToMapConverter;
import com.herms.enums.Condition;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "ATTRIBUTE_VALIDATION")
public class AttributeValidationEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private ResourceAttributeEntity resourceAttribute;
    @Convert(converter = ConditionConverter.class)
    @Column(name="CONDITION", length=3)
    private Condition condition;
    private String valueToCompare;
    @Convert(converter = StringToMapConverter.class)
    @Column(name="RESPONSE_ON_ERROR")
    private Map<String, Object> responseOnError;
    private Integer statusOnError;

    public AttributeValidationEntity() {
    }

    public AttributeValidationEntity(Long id, ResourceAttributeEntity resourceAttribute, Condition condition, String valueToCompare, Map<String, Object> responseOnError, Integer statusOnError) {
        this.id = id;
        this.resourceAttribute = resourceAttribute;
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

    public ResourceAttributeEntity getResourceAttribute() {
        return resourceAttribute;
    }

    public void setResourceAttribute(ResourceAttributeEntity resourceAttribute) {
        this.resourceAttribute = resourceAttribute;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getValueToCompare() {
        return valueToCompare;
    }

    public void setValueToCompare(String valueToCompare) {
        this.valueToCompare = valueToCompare;
    }

    public Map<String, Object> getResponseOnError() {
        return responseOnError;
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
