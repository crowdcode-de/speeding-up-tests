package io.crowdcode.speedup.bay.service.mockito.mock10test;

import io.crowdcode.speedup.bay.exception.AuctionNotFoundException;
import io.crowdcode.speedup.bay.model.Auction;
import io.crowdcode.speedup.bay.repository.AuctionRepository;
import io.crowdcode.speedup.bay.service.AuctionService;
import io.crowdcode.speedup.bay.service.AuctionServiceBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuctionServiceMockito04Test {


    private AuctionService auctionService;

    private AuctionRepository auctionRepository;

    @BeforeEach
    void setUp() {
        auctionRepository = Mockito.mock(AuctionRepository.class);
        auctionService = new AuctionServiceBean(auctionRepository);
    }

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