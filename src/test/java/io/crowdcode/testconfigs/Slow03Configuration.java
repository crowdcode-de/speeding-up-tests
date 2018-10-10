package io.crowdcode.testconfigs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static io.crowdcode.speedup.common.AnsiColor.yellow;

@Slf4j
@Configuration
public class Slow03Configuration {

    @Bean
    public String slowTest03Bean() throws InterruptedException {
        log.info(yellow("-------- Initializing a slow bean configuration 03 for 2 seconds"));
        TimeUnit.SECONDS.sleep(2);
        return "This is a slow test bean";
    }

}
