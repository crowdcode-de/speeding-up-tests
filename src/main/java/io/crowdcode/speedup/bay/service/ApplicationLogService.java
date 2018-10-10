package io.crowdcode.speedup.bay.service;


import io.crowdcode.speedup.bay.model.Message;

import java.time.Duration;
import java.util.List;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
public interface ApplicationLogService {

    void log(String message, Object... args);

    List<Message> lastLogs(Duration duration);

}
