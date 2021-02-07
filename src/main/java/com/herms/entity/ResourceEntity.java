package com.herms.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "RESOURCE")
public class ResourceEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "resource")
    @MapKey(name = "fieldName")
    private Map<String, ResourceAttributeEntity> attributesMap;
    @ManyToOne
    private UserEntity createdBy;

    public ResourceEntity() {
        attributesMap = new HashMap<>();
    }
    public ResourceEntity(Long id) {
        this();
        this.id = id;
    }

    public ResourceEntity(Long id, String name, Map<String, ResourceAttributeEntity> attributesMap, UserEntity createdBy) {
        this(id);
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

    public Map<String, ResourceAttributeEntity> getAttributesMap() {
        return attributesMap;
    }

    public void setAttributesMap(Map<String, ResourceAttributeEntity> attributesMap) {
        this.attributesMap = attributesMap;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }
}
