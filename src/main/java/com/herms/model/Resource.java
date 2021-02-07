package com.herms.model;

import com.herms.entity.ResourceAttributeEntity;
import com.herms.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    private Long id;
    private String name;
    private List<ResourceAttribute> attributesList;
    private User createdBy;

    public Resource() {
        attributesList = new ArrayList<>();
    }

    public Resource(Long id, String name, List<ResourceAttribute> attributesList, User createdBy) {
        this.id = id;
        this.name = name;
        this.attributesList = attributesList;
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

    public List<ResourceAttribute> getAttributesList() {
        return attributesList;
    }

    public void setAttributesList(List<ResourceAttribute> attributesList) {
        this.attributesList = attributesList;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
