package io.crowdcode.speedup.bay.test.howlong;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static io.crowdcode.speedup.common.AnsiColor.blue;
import static io.crowdcode.speedup.common.AnsiColor.green;

@Slf4j
public class Junit4_HowLongDoesItTakeInTest {

    private static LocalDateTime startTime;

    public Junit4_HowLongDoesItTakeInTest() {
        log.info(green("Constructor of HowLongDoesItTakeInJUnit " + Thread.currentThread().getName()));
    }


    @BeforeClass
    public static void beforeAll() {
        startTime = LocalDateTime.now();
        System.out.println(green("--------- started at: " + startTime + " " + Thread.currentThread().getName()));
    }

    @AfterClass
    public static void afterAll() {
        Duration duration = Duration.between(startTime, LocalDateTime.now());
        System.out.println(green("--------- finished at: " + LocalDateTime.now() + " " + Thread.currentThread().getName()));
        System.out.println(green("--------- duration is: " + (duration.get(ChronoUnit.NANOS) / 10000000.0) + " s."));
    }

    @Before
    public void setUp() throws InterruptedException {
        log.info(blue("-------- before each for 1s ----" + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void test_01() throws InterruptedException {
        log.info(blue("-------- test one for 1s ----" + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(1);
        Assertions.assertThat(Boolean.TRUE).isTrue();
    }

    @Test
    public void test_02() throws InterruptedException {
        log.info(blue("-------- test two for 2s ----" + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(2);
        Assertions.assertThat(Boolean.TRUE).isTrue();
    }

    @Test
    public void test_03() throws InterruptedException {
        log.info(blue("-------- test three for 3s ----" + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(3);
        Assertions.assertThat(Boolean.TRUE).isTrue();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        log.info(blue("-------- after each for 1s ----" + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(1);
    }

}
