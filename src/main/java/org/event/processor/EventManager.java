package org.event.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.event.dto.Event;
import org.event.dto.LogEvent;

public class EventManager {

    public static Map<String, Event> populateExpectedEvent(final List<LogEvent> logEvents) {
        final Map<String, Event> eventMap = new HashMap<>();
        for (LogEvent logEvent : logEvents) {
            Event event;
            if (eventMap.containsKey(logEvent.getId())) {
                event = eventMap.get(logEvent.getId());
            } else {
                event = new Event();
            }
            if (logEvent.getState().equals("STARTED")) {
                event.setStarted(logEvent);
            } else if (logEvent.getState().equals("FINISHED")) {
                event.setFinished(logEvent);
            }

            eventMap.put(logEvent.getId(), event);
        }
        return eventMap;
    }
}
