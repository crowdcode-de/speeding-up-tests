package io.crowdcode.speedup.bay.model;


import io.crowdcode.speedup.bay.exception.AuctionExpiredException;
import io.crowdcode.speedup.bay.exception.BidTooLowException;
import io.crowdcode.speedup.common.jpa.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Auction extends AbstractEntity {

    private String owner;

    private LocalDateTime beginDate;

    private LocalDateTime expireDate;

    private BigDecimal minAmount;

    private String title;

    private String description;

    private String productUuid;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Bid> bids = new ArrayList<>();

    public Bid getHighestBid() {
        return bids
                .stream()
                .max(Comparator.comparing(Bid::getAmount))
                .orElse(new Bid().setAmount(BigDecimal.ZERO).setEmail("-"));
    }


    public boolean isExpired() {
        return expireDate.isBefore(LocalDateTime.now());
    }

    public boolean isRunning() {
        return (beginDate.isBefore(LocalDateTime.now())
                || beginDate.isEqual(LocalDateTime.now()))
                && expireDate.isAfter(LocalDateTime.now());
    }

    public Auction addBid(Bid bid) throws BidTooLowException, AuctionExpiredException {
        if (isExpired()) {
            throw new AuctionExpiredException(id);
        }

        if (getHighestBid().getAmount().compareTo(bid.getAmount()) > 0) {
            throw new BidTooLowException(getHighestBid());
        }
        bids.add(bid);
        return this;
    }

    public Auction addBids(Bid... bids) {
        this.bids.addAll(Arrays.asList(bids));
        return this;
    }
}
