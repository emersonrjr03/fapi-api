package com.herms.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RESOURCE")
public class ResourceEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "resource")
    private List<ResourceAttributeEntity> attributesList;
    @ManyToOne
    private UserEntity createdBy;

    public ResourceEntity() {
        attributesList = new ArrayList<>();
    }
    public ResourceEntity(Long id) {
        this();
        this.id = id;
    }

    public ResourceEntity(Long id, String name, List<ResourceAttributeEntity> attributesList, UserEntity createdBy) {
        this(id);
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

    public List<ResourceAttributeEntity> getAttributesList() {
        return attributesList;
    }

    public void setAttributesList(List<ResourceAttributeEntity> attributesList) {
        this.attributesList = attributesList;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }
}
