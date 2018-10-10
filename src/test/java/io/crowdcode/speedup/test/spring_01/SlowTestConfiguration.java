package io.crowdcode.speedup.test.spring_01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

import static io.crowdcode.speedup.common.AnsiColor.blue;

@Slf4j
@TestConfiguration
public class SlowTestConfiguration {

    @Bean
    public String slowTestBean() throws InterruptedException {
        log.info(blue("-------- Initializing a slow bean for 5 seconds"));
        TimeUnit.SECONDS.sleep(5);
        return "This is a slow test bean";
    }

}
