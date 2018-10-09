package io.crowdcode.speedup.scrumr.repository;

import io.crowdcode.speedup.scrumr.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
