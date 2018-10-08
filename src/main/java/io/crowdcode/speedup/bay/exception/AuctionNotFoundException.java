package io.crowdcode.speedup.bay.exception;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionNotFoundException extends ApplicationException {

    public AuctionNotFoundException(Long auctionId) {
        super(String.format("Auction with id %d does not exist.", auctionId));
    }
}
