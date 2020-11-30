package io.github.batetolast1.wedderforecast.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class StringToLocalDateTimeConverter extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String value) {
        return ZonedDateTime.parse(value).toLocalDateTime();
    }
}
