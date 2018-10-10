package io.crowdcode.speedup.scrumr.service.inheritance;

import io.crowdcode.speedup.scrumr.repository.BacklogItemRepository;
import io.crowdcode.speedup.scrumr.repository.ProjectRepository;
import io.crowdcode.speedup.scrumr.repository.SprintRepository;
import io.crowdcode.speedup.scrumr.repository.UserRepository;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class AbstractScrumrMockBeanTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    ProjectRepository projectRepository;

    @MockBean
    SprintRepository sprintRepository;

    @MockBean
    BacklogItemRepository backlogItemRepository;


}
