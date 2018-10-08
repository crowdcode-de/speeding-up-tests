package io.crowdcode.speedup.common.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Caution this class is not daylight saving hours aware.
 *
 * @author Ingo Düppe (CROWDCODE)
 */
@Converter(autoApply = true)
public class LocalDateTimePersistenceConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        if (attribute != null) {
            ZoneId zoneId = ZoneId.systemDefault();
            return Timestamp.from(attribute.atZone(zoneId).toInstant());
        } else {
            return null;
        }
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        if (dbData != null) {
            return LocalDateTime.ofInstant(dbData.toInstant(), ZoneId.systemDefault());
        } else {
            return null;
        }
    }
}
