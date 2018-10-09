package io.crowdcode.speedup.scrumr.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Tag("entitytest")
class TaskEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testPersistFlustAndFindUser() {
        Task task = testEntityManager.persistFlushFind(
                new Task()
                        .setTitle("fasten the entity setup")
                        .setDescription("what to do to save time")
                        .setEstimation(5)
                        .setRemaining(5)
                        .setState(TaskState.TODO)
        );
        Assertions.assertThat(task.getId()).isNotNull();
    }

}