package io.crowdcode.speedup.test.spring_06_contextcache_mockbean_optimized;

import io.crowdcode.speedup.bay.service.ReportingServiceBean;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingServiceMockBean02Test extends AbstractMockingTest {

    @Autowired
    private ReportingServiceBean reportingServiceBean;

    @Test
    public void testCountMessages() {
        when(messageRepository.count()).thenReturn(12L);
        Long count = reportingServiceBean.getMessageCount();
        Assertions.assertThat(count).isEqualTo(12L);
    }
}