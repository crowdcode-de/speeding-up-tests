package io.crowdcode.speedup.test.spring_02_misshit;

import io.crowdcode.speedup.bay.service.AuctionService;
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

import static io.crowdcode.speedup.common.AnsiColor.blue;
import static io.crowdcode.speedup.common.AnsiColor.green;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(SlowTestConfiguration.class)
public class ExtendedSpring02Test {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private String fastBean;

    @Autowired
    private AuctionService auctionService;

    @Test
    void testOne() {
        log.info(blue("-------- test one: " + fastBean));
        Assertions.assertThat(auctionService).isNotNull();
        Assertions.assertThat(context).isNotNull();
    }

    @TestConfiguration
    public static class SecondTestConfiguration {
        @Bean
        String fastBean() {
            log.info(green("-------- Initializing a fast bean"));
            return "a fast bean of ExtendedSpring01Test";
        }
    }

}
