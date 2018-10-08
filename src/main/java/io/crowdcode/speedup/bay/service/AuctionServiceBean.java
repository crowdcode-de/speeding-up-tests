package io.crowdcode.speedup.bay.service;

import io.crowdcode.speedup.bay.exception.AuctionExpiredException;
import io.crowdcode.speedup.bay.exception.AuctionNotFoundException;
import io.crowdcode.speedup.bay.exception.BidTooLowException;
import io.crowdcode.speedup.bay.model.Auction;
import io.crowdcode.speedup.bay.model.Bid;
import io.crowdcode.speedup.bay.repository.AuctionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@Service
@Transactional
public class AuctionServiceBean implements AuctionService {


    private AuctionRepository auctionRepository;

    public AuctionServiceBean() {
    }

    @Autowired
    public AuctionServiceBean(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public Long placeAuction(String title, String description, BigDecimal minAmount) {
        return placeAuction(title, description, minAmount, "unknown");
    }

    public Long placeAuction(String title, String description, BigDecimal minAmount, String productUuid) {


        if (minAmount == null || minAmount.compareTo(BigDecimal.ONE) <= 0) {
            minAmount = BigDecimal.ONE;
        }

        Auction auction = new Auction()
                .setOwner("unknown") // TODO fetch principal from security context
                .setTitle(title)
                .setDescription(description)
                .setProductUuid(productUuid)
                .setMinAmount(minAmount)
                .setBeginDate(LocalDateTime.now())
                .setExpireDate(LocalDateTime.now().plusMinutes(5));

        Auction save = auctionRepository.save(auction);
        return save.getId();
    }

    public Auction findAuction(Long auctionId) {
        return auctionRepository.getOne(auctionId);
    }

    public List<Auction> findRunningAuctions() {
        final LocalDateTime now = LocalDateTime.now();
        return auctionRepository
                .findAll()
                .parallelStream()
                .filter(Auction::isRunning)
                .collect(Collectors.toList());
    }

    public List<Auction> findExpiredAuctions() {
        final LocalDateTime now = LocalDateTime.now();
        return auctionRepository
                .findAll()
                .parallelStream()
                .filter(Auction::isExpired)
                .collect(Collectors.toList());
    }

    public void bidOnAuction(final Long auctionId, BigDecimal amount) throws AuctionNotFoundException, AuctionExpiredException, BidTooLowException {
        Optional<Auction> auction = auctionRepository.findById(auctionId);

        if (!auction.isPresent()) {
            throw new AuctionNotFoundException(auctionId);
        }

        if (auction.get().isExpired()) {
            throw new AuctionExpiredException(auctionId);
        }

        if (auction.get().getMinAmount().compareTo(amount) >= 0
                || auction.get().getHighestBid().getAmount().compareTo(amount) >= 0) {
            throw new BidTooLowException(auction.get().getHighestBid());
        }

        Bid bid = new Bid().setAmount(amount).setEmail("unknown-bidder"); // Principal with his email
        auction.get().getBids().add(bid);

        auctionRepository.save(auction.get());
    }

    public AuctionRepository getAuctionRepository() {
        return auctionRepository;
    }
}
