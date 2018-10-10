package io.crowdcode.speedup.test.spring_01;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

import static io.crowdcode.speedup.common.AnsiColor.blue;
import static io.crowdcode.speedup.common.AnsiColor.red;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(SlowTestConfiguration.class)
public class ExtendedSpringTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private String evenSlowerBean;

    @Test
    void testOne() {
        log.info(blue("-------- test one: " + evenSlowerBean));
        Assertions.assertThat(context).isNotNull();
    }

    @TestConfiguration
    public static class SecondTestConfiguration {
        @Bean
        String evenSlowerBean() throws InterruptedException {
            log.info(red("-------- Initializing an even slower bean for 5 seconds ----"));
            TimeUnit.SECONDS.sleep(5);
            return "even slower";
        }
    }

}
