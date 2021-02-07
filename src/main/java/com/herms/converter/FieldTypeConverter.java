package com.herms.converter;

import com.herms.enums.FieldType;

import javax.persistence.AttributeConverter;

public class FieldTypeConverter  implements AttributeConverter<FieldType, String> {

    @Override
    public String convertToDatabaseColumn(FieldType attribute) {
        if(attribute != null) {
            return attribute.getCode();
        }
        return null;
    }

    @Override
    public FieldType convertToEntityAttribute(String dbData) {
        if(dbData != null) {
            return FieldType.toEnum(dbData);
        }
        return null;
    }
}
