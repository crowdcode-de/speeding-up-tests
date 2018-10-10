package io.crowdcode.speedup.test.spring_01;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.crowdcode.speedup.common.AnsiColor.blue;

@Slf4j
@ExtendWith(SpringExtension.class)
@JsonTest
@Import(SlowTestConfiguration.class)
public class AutoConfigureSpringTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private String slowTestBean;

    @Test
    void testOne() {
        log.info(blue("-------- test one: " + slowTestBean));
        Assertions.assertThat(context).isNotNull();
    }


}
