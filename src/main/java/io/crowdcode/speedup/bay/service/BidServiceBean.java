package io.crowdcode.speedup.bay.service;

import io.crowdcode.speedup.bay.exception.BidTooLowException;
import io.crowdcode.speedup.bay.model.Bid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class BidServiceBean {
    
    public boolean bidOnAuction(String auctionId, String amount) throws BidTooLowException {
        BigDecimal amountValue = createAmount(amount);
        throw new BidTooLowException(new Bid().setAmount(BigDecimal.TEN));
    }

    private BigDecimal createAmount(String amount) {
        amount = " WTF - For what reason is this prefix here? Just for creating exceptions, of couse"+amount;
        return BigDecimal.valueOf(tryFailTryHarder(amount));
    }

    public Double tryFailTryHarder(String number) {
        Double value = null;
        int tries = 0;
        do {
            try {
                value = parse(number);
            } catch (Exception ex) {
                number = number.substring(1);
                log.info("failed so trying harder now with {} ", number);
            }
        } while (value == null && tries<100);
        return value;
    }

    private Double parse(String number) {
        return Double.valueOf(number);
    }


}
