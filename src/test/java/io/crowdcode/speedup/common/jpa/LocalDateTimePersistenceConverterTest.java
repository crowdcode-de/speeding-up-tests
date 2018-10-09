package io.crowdcode.speedup.common.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class LocalDateTimePersistenceConverterTest {

    private final LocalDateTimePersistenceConverter converter = new LocalDateTimePersistenceConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        LocalDateTime dateTime = LocalDateTime.now();

        Timestamp dbData = converter.convertToDatabaseColumn(dateTime);

        Assertions.assertThat(Timestamp.valueOf(dateTime)).isEqualTo(dbData);
    }

    @Test
    public void testConvertToEntityAttribute() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        LocalDateTime dateTime = converter.convertToEntityAttribute(timestamp);

        Assertions.assertThat(dateTime).isEqualTo(timestamp.toLocalDateTime());
    }

    @Test
    public void testConvertAround() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime result = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(now));

        Assertions.assertThat(result).isEqualTo(now);
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
                for (int hour = 0; hour < 24; hour++) {
                    LocalDateTime dateTime = LocalDateTime.of(2019, month, dayOfMonth, hour, 0, 0);
                    LocalDateTime result = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(dateTime));
                    if (month != 3 || dayOfMonth != 31 || hour != 2)
                        Assertions.assertThat(result).isEqualTo(dateTime);
                }
            }
        }
    }

    @TestFactory
    public Stream<DynamicTest> dynamicRunThroughTheYear() {
        List<LocalDateTime> dateTimes = new LinkedList<>();
        List<Integer> maxDaysPerMonth = List.of(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        for (int month = 1; month <= 12; month++) {
            for (int dayOfMonth = 1; dayOfMonth <= maxDaysPerMonth.get(month - 1); dayOfMonth++) {
                for (int hour = 0; hour < 24; hour++) {
                    if (month != 3 || dayOfMonth != 31 || hour != 2)
                        dateTimes.add(LocalDateTime.of(2019, month, dayOfMonth, hour, 1, 1, 0));
                }
            }
        }
        return dateTimes.stream().map(
                lds -> dynamicTest(
                        "check converting " + lds.toString(),
                        () -> {
                            LocalDateTime result = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(lds));
                            Assertions.assertThat(result).isEqualTo(lds);
                        })
        );
    }

}