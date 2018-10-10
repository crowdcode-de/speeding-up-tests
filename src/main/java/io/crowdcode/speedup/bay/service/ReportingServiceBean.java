package io.crowdcode.speedup.bay.service;

import io.crowdcode.speedup.bay.repository.AuctionRepository;
import io.crowdcode.speedup.bay.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingServiceBean {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private MessageRepository messageRepository;

    public Long getMessageCount() {
        return messageRepository.count();
    }

    public Long getAuctionCount() {
        return auctionRepository.count();
    }

}
