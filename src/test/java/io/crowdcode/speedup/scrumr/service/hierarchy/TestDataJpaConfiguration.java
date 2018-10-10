package io.crowdcode.speedup.scrumr.service.hierarchy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

import static io.crowdcode.speedup.common.AnsiColor.blue;

@Slf4j
@TestConfiguration
@EnableJpaRepositories(basePackages = {"io.crowdcode.speedup.scrumr.repository"})
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager()
@EntityScan(basePackages = {"io.crowdcode.speedup.scrumr.model", "io.crowdcode.speedup.common"})
public class TestDataJpaConfiguration {

    public TestDataJpaConfiguration() {
        log.info(blue("------- scrumr hierarchy test configuration -------"));
    }

}
