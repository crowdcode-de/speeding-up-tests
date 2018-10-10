package io.crowdcode.speedup.bay.repository;

import io.crowdcode.speedup.bay.model.Message;
import io.crowdcode.speedup.config.DataJpaConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(DataJpaConfiguration.class)
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    private static Stream<Arguments> createTestMessages() {
        return IntStream.range(1, 1000)
                .mapToObj((i) -> Arguments.of("Message " + i));
    }

    @ParameterizedTest
    @MethodSource("createTestMessages")
    void testAddMessage(String msg) {
        messageRepository.save(new Message(msg));
        List<Message> messages = messageRepository
                .findLogs(LocalDateTime.now().minusSeconds(5), LocalDateTime.now());
        Assertions.assertThat(messages).isNotEmpty();
    }
}