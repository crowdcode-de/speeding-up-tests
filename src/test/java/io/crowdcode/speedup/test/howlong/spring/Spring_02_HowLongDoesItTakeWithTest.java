package io.crowdcode.speedup.test.howlong.spring;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

import static io.crowdcode.speedup.common.AnsiColor.blue;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Spring_02_HowLongDoesItTakeWithTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private String slow02TestBean;

    @Test
    void testContext() {
        log.info(blue("-------- test one: " + slow02TestBean));
        Assertions.assertThat(context).isNotNull();
    }

    @TestConfiguration
    public static class SlowTestConfiguration {

        @Bean
        public String slow02TestBean() throws InterruptedException {
            log.info(blue("-------- Initializing a slow bean for 3 seconds"));
            TimeUnit.SECONDS.sleep(3);
            return "This is a slow test bean";
        }
    }

}
