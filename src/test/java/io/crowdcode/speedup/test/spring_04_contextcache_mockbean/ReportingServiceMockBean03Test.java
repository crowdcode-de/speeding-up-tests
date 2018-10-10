package io.crowdcode.speedup.test.spring_04_contextcache_mockbean;

import io.crowdcode.speedup.bay.repository.AuctionRepository;
import io.crowdcode.speedup.bay.service.ReportingServiceBean;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingServiceMockBean03Test {

    @Autowired
    private ReportingServiceBean reportingServiceBean;

    @MockBean
    private AuctionRepository repository;

    @Test
    public void testCountAuction() {
        when(repository.count()).thenReturn(12L);
        Long count = reportingServiceBean.getAuctionCount();
        Assertions.assertThat(count).isEqualTo(12L);
    }
}