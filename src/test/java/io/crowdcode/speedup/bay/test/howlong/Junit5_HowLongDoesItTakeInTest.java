package io.crowdcode.speedup.bay.test.howlong;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static io.crowdcode.speedup.common.AnsiColor.blue;
import static io.crowdcode.speedup.common.AnsiColor.green;

@Slf4j
public class Junit5_HowLongDoesItTakeInTest {

    private static LocalDateTime startTime;

    public Junit5_HowLongDoesItTakeInTest() {
        log.info(green("Constructor of HowLongDoesItTakeInJUnit " + Thread.currentThread().getName()));
    }

    @BeforeAll
    static void beforeAll() {
        startTime = LocalDateTime.now();
        System.out.println(green("--------- started at: " + startTime + " " + Thread.currentThread().getName()));
    }

    @AfterAll
    static void afterAll() {
        Duration duration = Duration.between(startTime, LocalDateTime.now());
        System.out.println(green("--------- finished at: " + LocalDateTime.now() + " " + Thread.currentThread().getName()));
        System.out.println(green("--------- duration is: " + (duration.get(ChronoUnit.NANOS) / 10000000.0) + " s."));
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        log.info(blue("-------- before each for 1s ----" + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    void test_01() throws InterruptedException {
        log.info(blue("-------- test one for 1s ----" + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(1);
        Assertions.assertThat(Boolean.TRUE).isTrue();
    }

    @Test
    void test_02() throws InterruptedException {
        log.info(blue("-------- test two for 2s ----" + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(2);
        Assertions.assertThat(Boolean.TRUE).isTrue();
    }

    @Test
    void test_03() throws InterruptedException {
        log.info(blue("-------- test three for 3s ----" + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(3);
        Assertions.assertThat(Boolean.TRUE).isTrue();
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        log.info(blue("-------- after each for 1s ----" + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(1);
    }

}
