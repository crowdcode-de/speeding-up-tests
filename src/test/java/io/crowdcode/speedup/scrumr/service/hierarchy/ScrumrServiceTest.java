package io.crowdcode.speedup.scrumr.service.hierarchy;

import io.crowdcode.speedup.scrumr.repository.UserRepository;
import io.crowdcode.speedup.scrumr.service.ScrumrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static io.crowdcode.speedup.common.AnsiColor.blue;
import static org.assertj.core.api.Assertions.assertThat;

@ServiceDataTest
class ScrumrServiceTest {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ScrumrService taskService;

    @Test
    void testContextHierarchy() {

        ApplicationContext context = applicationContext;
        do {
            Arrays.stream(context.getBeanDefinitionNames())
                    .map(n -> blue(n))
                    .forEach(System.out::println);
            System.out.println("\n\n-------------\n\n");
            context = context.getParent();
        } while (context != null);

    }

    @Test
    void testCreateTask() {
        assertThat(taskService.createTask("TASK_X", 1)).isNotNull();
    }
}