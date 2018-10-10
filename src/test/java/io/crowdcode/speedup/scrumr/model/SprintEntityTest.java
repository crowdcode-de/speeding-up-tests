package io.crowdcode.speedup.scrumr.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Tag("entitytest")
class SprintEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testPersistFlustAndFindUser() {
        Sprint sprint = testEntityManager.persistFlushFind(
                new Sprint()
                        .setName("spring test a")
                        .setGoal("getting hierarchy and hierarchy")
                        .setStartDate(LocalDate.now())
                        .setEndDate(LocalDate.now().plusWeeks(2L))
                        .setCapacity(20)
        );
        Assertions.assertThat(sprint.getId()).isNotNull();
    }


}