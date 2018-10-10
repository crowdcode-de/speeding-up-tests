package io.crowdcode.speedup.bay.service.mockbean.mockbean03test;


import io.crowdcode.speedup.bay.exception.AuctionNotFoundException;
import io.crowdcode.speedup.bay.model.Auction;
import io.crowdcode.speedup.bay.repository.AuctionRepository;
import io.crowdcode.speedup.bay.service.AuctionService;
import io.crowdcode.speedup.test.annotations.MockBeanTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@MockBeanTest
public class AuctionServiceMockBean09Test {

    @Autowired
    private AuctionService auctionService;

    @MockBean
    private AuctionRepository auctionRepository;

    @Test
    void testPlaceAValidAuction() {
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
        when(auctionRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> auctionService.bidOnAuction(42L, BigDecimal.ONE))
                .isInstanceOf(AuctionNotFoundException.class)
                .hasMessageContaining("Auction with id 42 does not exist.");
    }

}
