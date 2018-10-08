package io.crowdcode.speedup.bay.controller;

import io.crowdcode.speedup.bay.fixture.AuctionFixture;
import io.crowdcode.speedup.bay.service.AuctionService;
import io.crowdcode.speedup.bay.web.AuctionRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AuctionControllerWebTest {

    @MockBean
    private AuctionService auctionService;

    @Autowired
    private WebTestClient testClient;

    @Test
    public void testWebClient() {
        Mockito.when(auctionService.placeAuction(any(), any(), any(), any())).thenReturn(4711L);
        AuctionRequest request = AuctionFixture.buildDefaultAuctionRequest();

        this.testClient
                .post()
                .uri("/api/v1/auctions")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), AuctionRequest.class)
                .exchange()
                .expectStatus().isCreated();
    }
}
