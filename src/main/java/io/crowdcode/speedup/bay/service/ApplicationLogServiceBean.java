package io.crowdcode.speedup.bay.service;

import io.crowdcode.speedup.bay.model.Message;
import io.crowdcode.speedup.bay.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Service
public class ApplicationLogServiceBean implements ApplicationLogService {

    @Autowired
    private MessageRepository logRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String message, Object... args) {
        String msg = String.format(message, args);
        Message messageEntity = new Message(msg);
        logRepository.save(messageEntity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Message> lastLogs(Duration duration) {
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minus(duration);

        return logRepository.findLogs(from, to);
    }
}
