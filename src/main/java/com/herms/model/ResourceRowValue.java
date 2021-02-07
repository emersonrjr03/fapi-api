package com.herms.model;

public class ResourceRowValue {

    private Long id;

    private String field;

    private String value;

    private Long rowId;

    public ResourceRowValue() {
    }

    public ResourceRowValue(Long id, String field, String value, Long rowId) {
        this.id = id;
        this.field = field;
        this.value = value;
        this.rowId = rowId;
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

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }
}
