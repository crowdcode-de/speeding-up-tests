package io.crowdcode.speedup.test.spring_06_contextcache_mockbean_optimized;

import io.crowdcode.speedup.bay.fixture.AuctionFixture;
import io.crowdcode.speedup.bay.model.Auction;
import io.crowdcode.speedup.bay.service.AuctionService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AuctionServiceMockBean06Test extends AbstractMockingTest {

    @Autowired
    private AuctionService auctionService;

    @Test
    public void testCountAuction() {
        when(auctionRepository.getOne(any())).thenReturn(AuctionFixture.buildDefaultAuction());
        Auction auction = auctionService.findAuction(1L);
        Assertions.assertThat(auction.getTitle()).isEqualTo(AuctionFixture.DEFAULT_AUCTION_TITLE);
    }
}