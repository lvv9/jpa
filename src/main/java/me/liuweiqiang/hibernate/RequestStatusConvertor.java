package me.liuweiqiang.hibernate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class RequestStatusConvertor implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.getCode();
    }

    @Override
    public Status convertToEntityAttribute(String s) {
        return Arrays.stream(Status.values()).filter(status -> status.getCode().equals(s)).findFirst().orElse(null);
    }
}
