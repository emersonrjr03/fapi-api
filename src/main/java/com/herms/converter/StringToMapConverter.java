package com.herms.converter;

import com.herms.utils.ConvertUtils;
import io.vertx.core.json.JsonObject;
import org.json.JSONObject;

import javax.json.stream.JsonParser;
import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StringToMapConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> value) {
        return ConvertUtils.mapToString(value);
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String value) {
        return ConvertUtils.stringToMap(value);
    }
}
