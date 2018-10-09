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
class BacklogItemEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testPersistFlushAndFindUser() {
        BacklogItem item = testEntityManager.persistFlushFind(
                new BacklogItem()
                        .setTitle("fasten the entity setup")
                        .setDescription("what to do to save time")
                        .setEstimation(5)
                        .setPriority(1)
        );
        Assertions.assertThat(item.getId()).isNotNull();
    }

}