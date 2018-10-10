package io.crowdcode.speedup.test.spring_01;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static io.crowdcode.speedup.common.AnsiColor.blue;
import static io.crowdcode.speedup.common.AnsiColor.green;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(SlowTestConfiguration.class)
public class HowLongDoesItTakeWithSpringTest {

    private static LocalDateTime startTime;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private String slowTestBean;

    @BeforeAll
    static void beforeAll() {
        startTime = LocalDateTime.now();
        System.out.println(green("--------- started at: " + startTime));
    }

    @AfterAll
    static void afterAll() {
        Duration duration = Duration.between(startTime, LocalDateTime.now());
        System.out.println(green("--------- finished at: " + LocalDateTime.now()));
        System.out.println(green("--------- duration is: " + duration.get(ChronoUnit.SECONDS) + " s."));
    }

    @Test
    void testContext() {
        log.info(blue("-------- test one: " + slowTestBean));
        Assertions.assertThat(context).isNotNull();
    }

}
