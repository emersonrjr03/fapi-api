package com.herms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
