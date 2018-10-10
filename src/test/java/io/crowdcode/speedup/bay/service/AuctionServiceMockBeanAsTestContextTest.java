package io.crowdcode.speedup.bay.service;


import io.crowdcode.speedup.bay.exception.AuctionNotFoundException;
import io.crowdcode.speedup.bay.model.Auction;
import io.crowdcode.speedup.bay.repository.AuctionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static io.crowdcode.speedup.common.AnsiColor.purple;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Execution(ExecutionMode.SAME_THREAD)
public class AuctionServiceMockBeanAsTestContextTest {

    @Autowired
    private AuctionService auctionService;

    @MockBean
    private AuctionRepository auctionRepository;

    @Test
    void testPlaceAValidAuction() {
        log.info(purple("auctionRepository Bean is of type {}"), auctionRepository.getClass().getName());

        //given
        Auction auction = Mockito.mock(Auction.class);
        when(auction.getId()).thenReturn(1L);
        when(auctionRepository.save(any())).thenReturn(auction);

        //when
        Long auctionId = auctionService.placeAuction("title", "description", BigDecimal.ONE);

        //then
        assertThat(auctionId).isEqualTo(1L);
        verify(auctionRepository, times(1)).save(any());
    }


    @Test
    void testBidOnNotExistingAuction() {
        log.info(purple("auctionRepository Bean is of type {}"), auctionRepository.getClass().getName());

        when(auctionRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> auctionService.bidOnAuction(42L, BigDecimal.ONE))
                .isInstanceOf(AuctionNotFoundException.class)
                .hasMessageContaining("Auction with id 42 does not exist.");

        verify(auctionRepository, atLeast(1)).findById(anyLong());
    }

}
