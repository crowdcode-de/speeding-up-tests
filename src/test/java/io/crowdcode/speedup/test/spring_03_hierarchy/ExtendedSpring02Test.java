package io.crowdcode.speedup.test.spring_03_hierarchy;

import io.crowdcode.testconfigs.Slow01Configuration;
import io.crowdcode.testconfigs.Slow02Configuration;
import io.crowdcode.testconfigs.Slow03Configuration;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.crowdcode.speedup.common.AnsiColor.blue;
import static io.crowdcode.speedup.common.AnsiColor.green;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextHierarchy(
        {
                @ContextConfiguration(name = "parent", classes = Slow01Configuration.class),
                @ContextConfiguration(name = "child01", classes = Slow02Configuration.class),
                @ContextConfiguration(name = "child02", classes = Slow03Configuration.class),
                @ContextConfiguration(name = "test", classes = ExtendedSpring02Test.SecondTestConfiguration.class)
        }
)
public class ExtendedSpring02Test {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private String fastBean;

    @Autowired
    private String slowTest01Bean;

    @Autowired
    private String slowTest02Bean;

    @Autowired
    private String slowTest03Bean;


    @Test
    void testOne() {
        log.info(blue("-------- test one: " + fastBean));
        Assertions.assertThat(context).isNotNull();
        Assertions.assertThat(slowTest01Bean).isNotNull();
        Assertions.assertThat(slowTest02Bean).isNotNull();
        Assertions.assertThat(slowTest03Bean).isNotNull();
    }

    @TestConfiguration
    public static class SecondTestConfiguration {
        @Bean
        String fastBean() {
            log.info(green("-------- Initializing a fast bean 02"));
            return "a fast bean of ExtendedSpring02Test";
        }
    }

}
