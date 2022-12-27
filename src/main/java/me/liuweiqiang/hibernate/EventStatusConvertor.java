package me.liuweiqiang.hibernate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class EventStatusConvertor implements AttributeConverter<EventStatus, String> {

    @Override
    public String convertToDatabaseColumn(EventStatus status) {
        return status.getCode();
    }

    @Override
    public EventStatus convertToEntityAttribute(String s) {
        return Arrays.stream(EventStatus.values()).filter(status -> status.getCode().equals(s)).findFirst().orElse(null);
    }
}
