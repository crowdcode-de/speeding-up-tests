package io.crowdcode.speedup.bay.fixture;

import io.crowdcode.speedup.bay.model.Auction;
import io.crowdcode.speedup.bay.model.Bid;
import io.crowdcode.speedup.bay.web.AuctionRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AuctionFixture {

    public static final String DEFAULT_AUCTION_TITLE = "My Favorite Notebook";
    public static final String DEFAULT_AUCTION_DESCRIPTION = "MacBook Pro 15\" Retina";
    public static final String DEFAULT_BID_EMAIL = "test@unit.org";

    public static Auction createTestAuction(String title, int amount) {
        return buildAuction()
                .setDescription("description")
                .setMinAmount(BigDecimal.ONE)
                .setTitle(title)
                .setBids(List.of(
                        new Bid()
                                .setAmount(BigDecimal.valueOf(amount))
                                .setEmail("kontakt@crowdcode.io")));
    }

    public static Auction buildDefaultAuction() {
        return buildAuction()
                .setTitle(DEFAULT_AUCTION_TITLE)
                .setMinAmount(BigDecimal.ONE)
                .setDescription(DEFAULT_AUCTION_DESCRIPTION)
                .addBids(buildLowBid(),
                        buildHighBid());
    }

    public static AuctionRequest buildDefaultAuctionRequest() {
        return new AuctionRequest()
                .setTitle("auction title")
                .setDescription("auction description")
                .setMinAmount(BigDecimal.ZERO)
                .setProductUuid("abcdef-unit-test");
    }

    public static Auction buildAuction() {
        return new Auction()
                .setBeginDate(LocalDateTime.now())
                .setExpireDate(LocalDateTime.now().plus(2, ChronoUnit.MINUTES))
                .setOwner("unittest");
    }

    public static Bid buildHighBid() {
        return new Bid()
                .setAmount(BigDecimal.TEN)
                .setEmail(DEFAULT_BID_EMAIL);
    }

    public static Bid buildLowBid() {
        return new Bid()
                .setAmount(BigDecimal.ONE)
                .setEmail(DEFAULT_BID_EMAIL);
    }

    public static Bid buildBid(String email, BigDecimal amount) {
        return new Bid()
                .setEmail(email)
                .setAmount(amount);
    }

}