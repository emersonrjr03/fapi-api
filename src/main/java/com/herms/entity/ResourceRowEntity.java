package com.herms.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RESOURCE_ROW")
public class ResourceRowEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ResourceEntity resource;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "row")
    private List<ResourceRowValueEntity> valueList;

    public ResourceRowEntity() {
        valueList = new ArrayList<>();
    }

    public ResourceRowEntity(Long id) {
        this.id = id;
    }

    public ResourceRowEntity(Long id, ResourceEntity resource, List<ResourceRowValueEntity> valueList) {
        this(id);
        this.resource = resource;
        this.valueList = valueList;
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

    public List<ResourceRowValueEntity> getValueList() {
        return valueList;
    }

    public void setValueList(List<ResourceRowValueEntity> valueList) {
        this.valueList = valueList;
    }
}
