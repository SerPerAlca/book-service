package com.eternum.book.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class BooleanNumericConverter implements AttributeConverter<Boolean, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final Boolean value) {
        return value == null ? null : (value ? 1 : 0);
    }

    @Override
    public Boolean convertToEntityAttribute(final Integer value) {
        return value != null && value == 1;
    }
}
