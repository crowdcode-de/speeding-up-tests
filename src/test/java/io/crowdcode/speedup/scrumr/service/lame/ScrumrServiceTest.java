package io.crowdcode.speedup.scrumr.service.lame;

import io.crowdcode.speedup.scrumr.service.ScrumrService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"spring.jpa.show-sql=true"})
class ScrumrServiceTest {

    @Autowired
    private ScrumrService taskService;


    @Test
    void testCreateTask() {
        assertThat(taskService.createTask("TASK_X", 1)).isNotNull();
    }
}