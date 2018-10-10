package io.crowdcode.speedup.test.spring_06_contextcache_mockbean_optimized;


import io.crowdcode.speedup.bay.repository.AuctionRepository;
import io.crowdcode.speedup.bay.repository.MessageRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class AbstractMockingTest {

    @MockBean
    protected AuctionRepository auctionRepository;

    @MockBean
    protected MessageRepository messageRepository;


    @Test
    public void testMockForAuctionRepositoryIsAvailable() {
        Assertions.assertThat(auctionRepository).isNotNull();
        Assertions.assertThat(auctionRepository.getClass().getName()).containsIgnoringCase("Mock");
    }

    @Test
    public void testMockForMessageRepositoryIsAvailable() {
        Assertions.assertThat(messageRepository).isNotNull();
        Assertions.assertThat(messageRepository.getClass().getName()).containsIgnoringCase("Mock");
    }
}
