package com.pluralsight.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

    public static LocalDateTime parse(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}
