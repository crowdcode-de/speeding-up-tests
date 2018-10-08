package io.crowdcode.speedup.bay.controller;

import io.crowdcode.speedup.bay.fixture.AuctionFixture;
import io.crowdcode.speedup.bay.service.AuctionService;
import io.crowdcode.speedup.bay.web.AuctionRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Not Thread Safe - MockBean leads in a shared mockito mock
 *
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureJsonTesters
public class AuctionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AuctionRequest> json;

    @MockBean
    private AuctionService auctionService;

    @Test
    public void testGetAuctions() throws Exception {
        when(auctionService.findRunningAuctions())
                .thenReturn(List.of(AuctionFixture.buildDefaultAuction()));

        this.mockMvc.perform(get("/api/v1/auctions"))
                .andExpect(status().isOk());

        verify(auctionService, atLeastOnce()).findRunningAuctions();
    }

    @Test
    public void testPostAuctions() throws Exception {
        when(auctionService.placeAuction(any(), any(), any(), any())).thenReturn(4711L);

        AuctionRequest auctionRequest = AuctionFixture.buildDefaultAuctionRequest();

        this.mockMvc.perform(
                post("/api/v1/auctions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(auctionRequest).getJson()))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"))
                .andExpect(header().string("location", containsString("/api/v1/auctions/4711")))
                .andDo(MockMvcResultHandlers.print());

        verify(auctionService, atLeastOnce()).placeAuction(any(), any(), any(), any());
    }
}