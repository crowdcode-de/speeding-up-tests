package io.crowdcode.speedup.test.contexthierarchy;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CConfiguration {

    @Bean
    public String c() {
        return "c";
    }
}
