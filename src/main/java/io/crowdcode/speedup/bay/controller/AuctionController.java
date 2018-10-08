package io.crowdcode.speedup.bay.controller;

import io.crowdcode.speedup.bay.exception.AuctionExpiredException;
import io.crowdcode.speedup.bay.exception.AuctionNotFoundException;
import io.crowdcode.speedup.bay.exception.BidTooLowException;
import io.crowdcode.speedup.bay.model.Auction;
import io.crowdcode.speedup.bay.model.Bid;
import io.crowdcode.speedup.bay.service.AuctionService;
import io.crowdcode.speedup.bay.web.AuctionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auctions")
public class AuctionController {

    private AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping
    public ResponseEntity<Void> placeAuction(@RequestBody AuctionRequest auctionRequest, UriComponentsBuilder uriComponentsBuilder) {
        Long auctionId = auctionService
                .placeAuction(
                        auctionRequest.getTitle(),
                        auctionRequest.getDescription(),
                        auctionRequest.getMinAmount(),
                        auctionRequest.getProductUuid());
        URI auctionURI = uriComponentsBuilder.pathSegment("api/v1/auctions/{auctionId}").buildAndExpand(auctionId).toUri();
        return ResponseEntity.created(auctionURI).build();
    }

    @GetMapping
    public List<Auction> listRunningAuctions() {
        return auctionService.findRunningAuctions();
    }

    @PostMapping("/{auctionId}")
    public ResponseEntity<Void> placeAuction(@PathVariable("auctionId") Long auctionId, @RequestBody Bid bid) throws BidTooLowException, AuctionExpiredException, AuctionNotFoundException {
        auctionService.bidOnAuction(auctionId, bid.getAmount());
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{auctionId}")
    public Auction auction(@PathVariable("auctionId") Long auctionId) {
        Auction auction = auctionService.findAuction(auctionId);
        return auction;
    }

    public AuctionService getAuctionService() {
        return auctionService;
    }
}
