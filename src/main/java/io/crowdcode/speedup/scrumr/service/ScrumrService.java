package io.crowdcode.speedup.scrumr.service;

import io.crowdcode.speedup.scrumr.model.Task;
import io.crowdcode.speedup.scrumr.model.TaskState;
import io.crowdcode.speedup.scrumr.repository.BacklogItemRepository;
import io.crowdcode.speedup.scrumr.repository.ProjectRepository;
import io.crowdcode.speedup.scrumr.repository.SprintRepository;
import io.crowdcode.speedup.scrumr.repository.TaskRepository;
import io.crowdcode.speedup.scrumr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrumrService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BacklogItemRepository backlogItemRepository;

    public Long createTask(String title, Integer estimation) {
        return taskRepository.save(new Task()
                .setTitle(title)
                .setEstimation(estimation)
                .setState(TaskState.TODO)).getId();
    }


}
