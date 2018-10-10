package io.crowdcode.speedup.scrumr.service.lame;

import io.crowdcode.speedup.scrumr.repository.UserRepository;
import io.crowdcode.speedup.scrumr.service.ScrumrService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"spring.jpa.show-sql=true"})
class ScrumrServiceMockUserRepositoryTest {

    @Autowired
    private ScrumrService taskService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testCreateTask() {
        when(userRepository.count()).thenReturn(1L);
        assertThat(taskService.createTask("TASK_X", 1)).isNotNull();
    }
}