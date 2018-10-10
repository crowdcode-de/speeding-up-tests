package io.crowdcode.speedup.scrumr.service.hierarchy;

import io.crowdcode.speedup.scrumr.repository.ProjectRepository;
import io.crowdcode.speedup.scrumr.service.ScrumrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ServiceDataTest
class ScrumrServiceMockProjectRepositoryTest {

    @Autowired
    private ScrumrService taskService;

    @MockBean
    private ProjectRepository projectRepository;

    @Test
    void testCreateTask() {
        when(projectRepository.count()).thenReturn(1L);
        assertThat(taskService.createTask("TASK_X", 1)).isNotNull();
    }
}