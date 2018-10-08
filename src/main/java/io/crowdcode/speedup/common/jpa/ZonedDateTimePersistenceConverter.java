package io.crowdcode.speedup.common.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimePersistenceConverter implements AttributeConverter<ZonedDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        if (attribute != null) {
            return Timestamp.from(attribute.toInstant());
        } else {
            return null;
        }
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        if (dbData != null) {
            return ZonedDateTime.ofInstant(dbData.toInstant(), ZoneId.systemDefault());
        } else {
            return null;
        }
    }

}
