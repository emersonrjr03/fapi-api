package com.herms.enums;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public enum FieldType {
    BOOLEAN("B"),
    DATE("D"),
    INTEGER("I"),
    DOUBLE("O"),
    TEXT("T");

    private String code;

    FieldType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public static FieldType toEnum(String code){
        for(FieldType type : values()){
            if(type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
