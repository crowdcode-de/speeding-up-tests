package io.crowdcode.speedup.test.spring_06_contextcache_mockbean_optimized;

import io.crowdcode.speedup.bay.repository.AuctionRepository;
import io.crowdcode.speedup.bay.repository.MessageRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * This doesn't work. You cannot mock beans this way.
 * Because BeanPostProcessor or AOP will replace it.
 * For instance, the auction repository mock will be replaced by the AOP DataJPA advice to create the normal repository.
 *
 * @author Ingo Düppe (CROWDCODE)
 */
@TestConfiguration
public class TestMockConfiguration {

    @Bean
    public AuctionRepository repositoryRepository() {
        return Mockito.mock(AuctionRepository.class);
    }

    @Bean
    public MessageRepository messageRepository() {
        return Mockito.mock(MessageRepository.class);
    }

}
