package com.herms.converter;

import com.herms.enums.Condition;
import com.herms.enums.FieldType;

import javax.persistence.AttributeConverter;

public class ConditionConverter implements AttributeConverter<Condition, String> {

    @Override
    public String convertToDatabaseColumn(Condition attribute) {
        if(attribute != null) {
            return attribute.getCode();
        }
        return null;
    }

    @Override
    public Condition convertToEntityAttribute(String dbData) {
        if(dbData != null) {
            return Condition.toEnum(dbData);
        }
        return null;
    }
}
