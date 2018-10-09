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
public class UserEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testPersistFlustAndFindUser() {
        User user = testEntityManager.persistFlushFind(
                new User()
                        .setName("JUNIT")
                        .setEmail("user-junit-test@crowdcode.io")
                        .setAdmin(false)
                        .setPassword("masterkey")
        );
        Assertions.assertThat(user.getId()).isNotNull();
    }
}