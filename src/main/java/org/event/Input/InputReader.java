package org.event.Input;

import java.util.List;
import java.util.Map;
import org.event.dto.Event;
import org.event.dto.LogEvent;

/**
 * Interface defining the methods to read log event from Input source.
 */
public interface InputReader {
    /**
     * Method to read log event from input source.
     * @return List<LogEvent>
     *          list of log event
     */
    List<LogEvent> read();
}
