package com.herms.utils;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConvertUtils {

    public static String dateToString(Date date){
        return dateToString(date, "dd/MM/yyyy");
    }

    public static String dateToString(Date date, String format){
        String dateStr = "";
        dateStr = new SimpleDateFormat(format).format(date);
        return dateStr;
    }

    public static Date stringToDate(String dateStr){
        return stringToDate(dateStr, "dd/MM/yyyy");
    }
    public static Date stringToDate(String dateStr, String format){
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
        }
        return date;
    }

    public static Map<String, Object> stringToMap(String jsonStr){
        JSONObject jsonObj = new JSONObject(jsonStr);

        @SuppressWarnings("unchecked")
        Iterator<String> nameItr = jsonObj.keys();
        Map<String, Object> outMap = new HashMap<>();
        while(nameItr.hasNext()) {
            String name = nameItr.next();
            outMap.put(name, jsonObj.getString(name));

        }
        return outMap;
    }

    public static String mapToString(Map<String, Object> map){
        JSONObject obj = new JSONObject();
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            obj.put(entry.getKey(), entry.getValue());
        }
        return obj.toString();
    }
}
