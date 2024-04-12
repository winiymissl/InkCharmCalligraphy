package com.example.module_community.dao.converter;

import androidx.room.TypeConverter;

import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:25
 * @Version 1.0
 */
public class ConverterImageURI {
    @TypeConverter
    public static List<String> fromString(String value) {
        return value == null ? null : List.of(value.split(","));
    }

    @TypeConverter
    public static String listToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s).append(",");
        }
        return stringBuilder.toString();
    }
}
