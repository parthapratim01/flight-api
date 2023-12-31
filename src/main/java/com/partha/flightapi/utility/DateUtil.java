package com.partha.flightapi.utility;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */
public class DateUtil {

    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime toLocalDateTime(String strDate) {
        return LocalDateTime.parse(strDate, dateFormatter);
    }

    public static LocalTime toLocalTime(String strDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
        LocalTime t = LocalTime.parse(strDate, formatter);
        return t;
    }
}
