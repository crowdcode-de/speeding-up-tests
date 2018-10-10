package io.crowdcode.speedup.test.spring_04_contextcache_mockbean;

import io.crowdcode.speedup.bay.fixture.AuctionFixture;
import io.crowdcode.speedup.bay.model.Auction;
import io.crowdcode.speedup.bay.repository.AuctionRepository;
import io.crowdcode.speedup.bay.service.AuctionService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuctionServiceMockBean06Test {

    @Autowired
    private AuctionService auctionService;

    @MockBean
    private AuctionRepository repository;

    @Test
    public void testCountAuction() {
        when(repository.getOne(any())).thenReturn(AuctionFixture.buildDefaultAuction());
        Auction auction = auctionService.findAuction(1L);
        Assertions.assertThat(auction.getTitle()).isEqualTo(AuctionFixture.DEFAULT_AUCTION_TITLE);
    }
}