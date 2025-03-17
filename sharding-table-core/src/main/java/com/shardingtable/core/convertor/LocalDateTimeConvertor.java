package com.shardingtable.core.convertor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTimeConverter
 *
 * @author hytl
 */
public class LocalDateTimeConvertor implements ShardValueConvertor<LocalDateTime> {
    @Override
    public String convert(LocalDateTime value, String rule) {
        return DateTimeFormatter.ofPattern(rule).format(value);
    }
}