package org.event.dto;

public class Event {
    LogEvent started;
    LogEvent finished;

    public LogEvent getStarted() {
        return started;
    }

    public void setStarted(LogEvent started) {
        this.started = started;
    }

    public void setFinished(LogEvent finished) {
        this.finished = finished;
    }

    public LogEvent getFinished() {
        return finished;
    }

    @Override
    public String toString() {
        return "Event{" + "started=" + started + ", finished=" + finished + '}';
    }
}
