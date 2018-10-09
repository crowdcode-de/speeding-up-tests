package io.crowdcode.speedup.scrumr.repository;

import io.crowdcode.speedup.scrumr.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
