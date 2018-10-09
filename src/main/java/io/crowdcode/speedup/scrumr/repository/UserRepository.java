package io.crowdcode.speedup.scrumr.repository;

import io.crowdcode.speedup.scrumr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
