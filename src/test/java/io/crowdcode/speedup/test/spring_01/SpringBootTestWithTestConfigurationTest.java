package io.crowdcode.speedup.test.spring_01;

import io.crowdcode.speedup.bay.controller.AuctionController;
import io.crowdcode.speedup.bay.service.AuctionService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.crowdcode.speedup.common.AnsiColor.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Execution(ExecutionMode.CONCURRENT)
public class SpringBootTestWithTestConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private String fastBean;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionController auctionController;

    @Test
    void testOne() {
        log.info(blue("-------- test one: " + fastBean));
        log.info(purple("-------- auctionService: " + auctionService.getClass().getName()));
        log.info(purple("-------- auctionController: " + auctionController.getClass().getName()));

        Assertions.assertThat(auctionService).isNotNull();
        Assertions.assertThat(context).isNotNull();
    }

    @TestConfiguration
    public static class SecondTestConfiguration {

        @Bean
        public String fastBean() {
            log.info(green("-------- Initializing a fast bean"));
            return "a fast bean of ExtendedSpring01Test";
        }

        @Bean
        public AuctionService auctionService() {
            log.info(red("-------- Replace auctionService by mock"));
            return Mockito.mock(AuctionService.class);
        }
    }

}
