package io.crowdcode.speedup.common.jpa;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;

public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        if (attribute != null) {
            return Date.valueOf(attribute);
        } else {
            return null;
        }
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        if (dbData != null) {
            return dbData.toLocalDate();
        } else {
            return null;
        }
    }
}
