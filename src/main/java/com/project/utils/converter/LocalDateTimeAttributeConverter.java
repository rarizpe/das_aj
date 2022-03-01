package com.project.utils.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply=true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime entityAttribute) {
        if (entityAttribute == null) {
            return null;
        }

        return Timestamp.valueOf(entityAttribute);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp databaseColumn) {
        if (databaseColumn == null) {
            return null;
        }

        return databaseColumn.toLocalDateTime();
    }
}
