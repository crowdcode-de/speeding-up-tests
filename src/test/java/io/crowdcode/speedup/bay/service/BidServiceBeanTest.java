package io.crowdcode.speedup.bay.service;

import io.crowdcode.speedup.bay.exception.BidTooLowException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BidServiceBeanTest {

    @Test()
    void test(@Autowired BidServiceBean bidService) {
        assertThatThrownBy(() -> bidService.bidOnAuction("12","23.00"))
                .isInstanceOf(BidTooLowException.class);
    }
}