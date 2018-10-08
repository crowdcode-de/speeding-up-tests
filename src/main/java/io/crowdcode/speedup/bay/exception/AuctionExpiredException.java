package io.crowdcode.speedup.bay.exception;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionExpiredException extends ApplicationException {

    public AuctionExpiredException(Long auctionId) {
        super("Auction" + auctionId + " already expired ");
    }

}
