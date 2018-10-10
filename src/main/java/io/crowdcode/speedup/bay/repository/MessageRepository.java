package io.crowdcode.speedup.bay.repository;

import io.crowdcode.speedup.bay.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.createdAt between :from AND :to ORDER BY m.createdAt DESC")
    List<Message> findLogs(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
