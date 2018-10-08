package io.crowdcode.speedup.bay.service;


import io.crowdcode.speedup.bay.exception.AuctionExpiredException;
import io.crowdcode.speedup.bay.exception.AuctionNotFoundException;
import io.crowdcode.speedup.bay.exception.BidTooLowException;
import io.crowdcode.speedup.bay.model.Auction;
import io.crowdcode.speedup.bay.repository.AuctionRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
public interface AuctionService {

    Long placeAuction(String title, String description, BigDecimal minAmount);

    Long placeAuction(String title, String description, BigDecimal minAmount, String productUuid);

    Auction findAuction(Long auctionId);

    List<Auction> findRunningAuctions();

    List<Auction> findExpiredAuctions();

    void bidOnAuction(final Long auctionId, BigDecimal amount) throws AuctionNotFoundException, AuctionExpiredException, BidTooLowException;

    AuctionRepository getAuctionRepository();

}
