package org.event.processor;

import org.event.dto.LogEvent;

public class LogEventBuilder {
    private String id;
    private String state;
    private String type;
    private String host;
    private long timestamp;

    public LogEventBuilder setEventId(String id) {
        this.id = id;
        return this;
    }

    public LogEventBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public LogEventBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public LogEventBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public LogEventBuilder setDuration(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public LogEvent build() {
        final LogEvent logEvent = new LogEvent();
        logEvent.setId(id);
        logEvent.setState(state);
        logEvent.setHost(host);
        logEvent.setType(type);
        logEvent.setTimestamp(timestamp);
        return logEvent;
    }
}
