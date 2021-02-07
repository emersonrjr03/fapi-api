package com.herms.model;

import java.util.HashMap;
import java.util.Map;

public class Resource {
    private Long id;
    private String name;
    private Map<String, ResourceAttribute> attributesMap;
    private User createdBy;

    public Resource() {
        attributesMap = new HashMap<>();
    }

    public Resource(Long id, String name, Map<String, ResourceAttribute> attributesMap, User createdBy) {
        this.id = id;
        this.name = name;
        this.attributesMap = attributesMap;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, ResourceAttribute> getAttributesMap() {
        return attributesMap;
    }

    public void setAttributesMap(Map<String, ResourceAttribute> attributesMap) {
        this.attributesMap = attributesMap;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
