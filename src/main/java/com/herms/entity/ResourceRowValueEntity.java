package com.herms.entity;

import javax.persistence.*;

@Entity
@Table(name = "RESOURCE_ROW_VALUE")
public class ResourceRowValueEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String field;

    private String value;

    @ManyToOne
    private ResourceRowEntity row;

    public ResourceRowValueEntity() {
    }

    public ResourceRowValueEntity(Long id) {
        this.id = id;
    }

    public ResourceRowValueEntity(Long id, String field, String value, ResourceRowEntity row) {
        this(id);
        this.field = field;
        this.value = value;
        this.row = row;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ResourceRowEntity getRow() {
        return row;
    }

    public void setRow(ResourceRowEntity row) {
        this.row = row;
    }
}
