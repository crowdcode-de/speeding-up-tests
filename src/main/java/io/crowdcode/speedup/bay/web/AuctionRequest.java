package io.crowdcode.speedup.bay.web;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class AuctionRequest {

    private String title;
    private String description;
    private BigDecimal minAmount;
    private String productUuid;
}
