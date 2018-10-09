package io.crowdcode.speedup.scrumr.repository;

import io.crowdcode.speedup.scrumr.fixture.ScrumrFixture;
import io.crowdcode.speedup.common.jpa.AbstractEntity;
import io.crowdcode.speedup.config.DataJpaConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@DataJpaTest()
@Import(DataJpaConfiguration.class)
@Tag("repository_test")
class BacklogItemRepositoryTest {

    @Autowired
    private BacklogItemRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testUserCreate() {
        AbstractEntity entity = repository.save(ScrumrFixture.defaultBacklogItem());
        testEntityManager.flush();
        Assertions.assertThat(entity.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
    }
}