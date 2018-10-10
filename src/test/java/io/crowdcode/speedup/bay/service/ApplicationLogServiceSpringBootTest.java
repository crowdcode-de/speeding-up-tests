package io.crowdcode.speedup.bay.service;

import io.crowdcode.speedup.bay.model.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationLogServiceSpringBootTest {

    @Autowired
    private ApplicationLogService applicationLogService;

    @Test
    void testLoggingMessageAndFindingThem() {
        applicationLogService.log("any log message");

        List<Message> messages = applicationLogService.lastLogs(Duration.of(5, ChronoUnit.SECONDS));

        assertThat(messages).hasAtLeastOneElementOfType(Message.class);
    }
}