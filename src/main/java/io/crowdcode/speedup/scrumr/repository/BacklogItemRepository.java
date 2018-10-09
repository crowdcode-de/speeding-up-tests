package io.crowdcode.speedup.scrumr.repository;

import io.crowdcode.speedup.scrumr.model.BacklogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogItemRepository extends JpaRepository<BacklogItem, Long> {
}
