package io.crowdcode.speedup.bay.service;

import io.crowdcode.speedup.bay.exception.AuctionNotFoundException;
import io.crowdcode.speedup.bay.model.Auction;
import io.crowdcode.speedup.bay.repository.AuctionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuctionServiceMockitoTest {

    @InjectMocks
    private AuctionService auctionService = new AuctionServiceBean();

    @Mock
    private AuctionRepository auctionRepository;

    @Test
    public void testPlaceAValidAuction() {
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
    public void testBidOnNotExistingAuction() {
        when(auctionRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> auctionService.bidOnAuction(42L, BigDecimal.ONE))
                .isInstanceOf(AuctionNotFoundException.class)
                .hasMessageContaining("Auction with id 42 does not exist.");
    }
}