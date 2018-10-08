package io.crowdcode.speedup.bay.service;

import io.crowdcode.speedup.bay.model.Auction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AuctionServiceSpringBootTest {

    @Autowired
    private AuctionService auctionService;

    @Test
    public void testPlaceAValidAuction() {
        Long auctionId = auctionService.placeAuction("title", "description", BigDecimal.ONE);
        assertThat(auctionId).isNotNull();
        Auction auction = auctionService.findAuction(auctionId);
        assertThat(auction)
                .hasFieldOrPropertyWithValue("title", "title")
                .hasFieldOrPropertyWithValue("description", "description")
                .hasFieldOrPropertyWithValue("minAmount", BigDecimal.ONE);
    }

}
