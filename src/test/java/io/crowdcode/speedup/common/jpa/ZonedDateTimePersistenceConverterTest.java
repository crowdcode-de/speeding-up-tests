package io.crowdcode.speedup.common.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class ZonedDateTimePersistenceConverterTest {

    private final ZonedDateTimePersistenceConverter converter = new ZonedDateTimePersistenceConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        ZonedDateTime dateTime = ZonedDateTime.now();

        Timestamp dbData = converter.convertToDatabaseColumn(dateTime);

        Assertions.assertThat(Timestamp.from(dateTime.toInstant())).isEqualTo(dbData);
    }

    @Test
    public void testConvertToEntityAttribute() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ZonedDateTime dateTime = converter.convertToEntityAttribute(timestamp);

        Assertions.assertThat(Timestamp.from(dateTime.toInstant())).isEqualTo(timestamp);
    }

    @Test
    public void testConvertAround() {
        ZonedDateTime now = ZonedDateTime.now();

        ZonedDateTime result = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(now));

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
                    ZonedDateTime dateTime = ZonedDateTime.of(2019, month, dayOfMonth, hour, 1, 1, 0, ZoneId.systemDefault());
                    ZonedDateTime result = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(dateTime));
                    Assertions.assertThat(result).isEqualTo(dateTime);
                }
            }
        }
    }


    @TestFactory
    @Tag("dynamic")
    Stream<DynamicTest> dynamicTests() {
        return IntStream.range(1, 1 + new Random().nextInt(10))
                .mapToObj(i -> dynamicTest("rndTest" + i, () -> assertTrue(i > 0)));
    }

    @TestFactory
    @Tag("dynamic")
    public Stream<DynamicTest> dynamicRunThroughTheYear() {
        List<ZonedDateTime> dateTimes = new LinkedList<>();
        List<Integer> maxDaysPerMonth = List.of(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        for (int month = 1; month <= 12; month++) {
            for (int dayOfMonth = 1; dayOfMonth <= maxDaysPerMonth.get(month - 1); dayOfMonth++) {
                for (int hour = 0; hour < 24; hour++) {
                    dateTimes.add(ZonedDateTime.of(2019, month, dayOfMonth, hour, 1, 1, 0, ZoneId.systemDefault()));
                }
            }
        }
        return dateTimes.stream().map(
                zdt -> dynamicTest(
                        "check converting " + zdt.toString(),
                        () -> {
                            ZonedDateTime result = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(zdt));
                            Assertions.assertThat(result).isEqualTo(zdt);
                        })
        );
    }

}