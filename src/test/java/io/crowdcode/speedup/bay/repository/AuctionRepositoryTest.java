package io.crowdcode.speedup.bay.repository;

import io.crowdcode.speedup.bay.exception.AuctionExpiredException;
import io.crowdcode.speedup.bay.exception.BidTooLowException;
import io.crowdcode.speedup.bay.fixture.AuctionFixture;
import io.crowdcode.speedup.bay.model.Auction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuctionRepositoryTest {

    public static final String EMAIL = "here@jcon.de";

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findAllAuctionsWithABidOfMime() throws BidTooLowException, AuctionExpiredException {
        // given
        Auction auction = AuctionFixture.buildAuction()
                .addBid(AuctionFixture
                        .buildBid(EMAIL, BigDecimal.valueOf(15)));

        testEntityManager.persistAndFlush(auction);
        testEntityManager.persistAndFlush(AuctionFixture.buildDefaultAuction());
        testEntityManager.persistAndFlush(AuctionFixture.buildDefaultAuction());
        testEntityManager.persistAndFlush(AuctionFixture.buildDefaultAuction());

        //when
        List<Auction> auctions = auctionRepository.findAllAuctionWithABidFrom(EMAIL);

        //then
        assertThat(auctions).hasOnlyOneElementSatisfying(
                (a) -> assertThat(a.getBids()).hasOnlyOneElementSatisfying(
                        (bid) -> assertThat(bid.getEmail()).isEqualTo(EMAIL)));
    }
}