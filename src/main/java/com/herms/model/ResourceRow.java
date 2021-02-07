package com.herms.model;

import java.util.ArrayList;
import java.util.List;

public class ResourceRow {

    private Long id;
    private Long resourceId;
//    OR CAN BE FieldRecord
    private List<ResourceRowValue> valueList;

    public ResourceRow() {
        valueList = new ArrayList<>();

    }

    public ResourceRow(Long id, Long resourceId, List<ResourceRowValue> data) {
        this.id = id;
        this.resourceId = resourceId;
        this.valueList = data;
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

    public List<ResourceRowValue> getValueList() {
        return valueList;
    }

    public void setValueList(List<ResourceRowValue> valueList) {
        this.valueList = valueList;
    }
}
