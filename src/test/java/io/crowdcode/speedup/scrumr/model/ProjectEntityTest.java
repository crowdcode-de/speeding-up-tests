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
public class ProjectEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testPersistFlustAndFindUser() {
        Project project = testEntityManager.persistFlushFind(
                new Project()
                        .setName("Speed up Spring Boot Integration Tests")
                        .setProductOwner(new User("username", "junit-po@crowdcode.io", "xxx", "po", false))
                        .setDescription("Getting hierarchy and hierarchy")
                        .setScrumMaster(new User("scrummaster", "junit-sm@crowdcode.io", "xxx", "sm", false))
        );
        Assertions.assertThat(project.getId()).isNotNull();
    }


}