package io.crowdcode.speedup.bay.exception;


import io.crowdcode.speedup.bay.model.Bid;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class BidTooLowException extends ApplicationException {
    public BidTooLowException(Bid highestBid) {
        super("The bid is too low. Current highestBid is " + highestBid.toString());
    }
}
