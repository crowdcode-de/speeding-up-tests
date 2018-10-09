package io.crowdcode.speedup.common.jpa;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class LocalDatePersistenceConverterTest {


    private final LocalDatePersistenceConverter converter = new LocalDatePersistenceConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        LocalDate attribute = LocalDate.now();

        Date dbData = converter.convertToDatabaseColumn(attribute);

        Assertions.assertThat(Date.valueOf(attribute)).isEqualTo(dbData);
    }

    @Test
    public void testConvertToEntityAttribute() {
        Date attribute = new Date(System.currentTimeMillis());

        LocalDate result = converter.convertToEntityAttribute(attribute);

        Assertions.assertThat(result).isEqualTo(attribute.toLocalDate());
    }

    @Test
    public void testConvertAround() {
        LocalDate today = LocalDate.now();

        LocalDate result = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(today));

        Assertions.assertThat(result).isEqualTo(today);
    }

    @Test
    public void testNullDatabaseColumn() {
        Assertions.assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    public void testNullEntityAttribute() {
        Assertions.assertThat(converter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    public void testRunThroughYear() {
        List<Integer> maxDaysPerMonth = List.of(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        for (int month = 1; month <= 12; month++) {
            for (int dayOfMonth = 1; dayOfMonth <= maxDaysPerMonth.get(month - 1); dayOfMonth++) {
                LocalDate day = LocalDate.of(2019, month, dayOfMonth);
                LocalDate result = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(day));
                Assertions.assertThat(result).isEqualTo(day);
            }
        }
    }
}