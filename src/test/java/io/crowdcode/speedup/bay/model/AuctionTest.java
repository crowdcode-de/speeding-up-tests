package io.crowdcode.speedup.bay.model;


import io.crowdcode.speedup.bay.exception.BidTooLowException;
import io.crowdcode.speedup.bay.fixture.AuctionFixture;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AuctionTest {

    @Test
    public void testGetHighestBid() {
        Assertions.assertThat(AuctionFixture.buildDefaultAuction().getHighestBid().getAmount()).isEqualTo(BigDecimal.valueOf(10));
    }

    @Test
    public void testAddTooLowBid() {
        Auction auction = AuctionFixture.buildDefaultAuction();

        assertThatThrownBy(() -> auction.addBid(AuctionFixture.buildLowBid()))
                .isInstanceOf(BidTooLowException.class)
                .hasMessageContaining("The bid is too low.");
    }

    @Test
    public void testAddNewHighestBid() throws Exception {
        Auction auction = AuctionFixture.buildDefaultAuction();
        auction.addBid(AuctionFixture.buildBid("here@jcon.de", BigDecimal.valueOf(15)));
        Assertions.assertThat(auction.getHighestBid()).hasFieldOrPropertyWithValue("email", "here@jcon.de");
    }
}