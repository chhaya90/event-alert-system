package org.event.processor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.event.database.writer.IWriter;
import org.event.dto.AlertEvent;
import org.event.dto.Event;
import org.event.dto.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlertManager.class);
    private final IWriter writer;

    public AlertManager(final IWriter writer) {
        this.writer = writer;
    }

    public void createAlert(final List<LogEvent> logEvents) {
        if(logEvents == null) {
            LOGGER.info("No logs received from input source to process.");
            return;
        }
        final Map<String,Event> eventLogs = EventManager.populateExpectedEvent(logEvents);
        ExecutorService service = Executors.newFixedThreadPool(10);
        eventLogs.keySet().forEach(id -> {
            final long start = eventLogs.get(id).getStarted().getTimestamp();
            final long finish = eventLogs.get(id).getFinished().getTimestamp();
            final long duration = finish - start;
            if ((duration) > 4) {
                AlertEvent alertEvent = constructAlert(id, eventLogs.get(id).getStarted(), duration);
                service.execute(() -> {
                    try {
                        writer.performInsert(alertEvent);
                        LOGGER.info("Event alert written for id: {}", alertEvent.getId());
                    } catch (Exception e) {
                        LOGGER.error("Error writing alert event for id : {}", alertEvent.getId());
                    }
                });
            }
        });

        while (!service.isTerminated()) {
            service.shutdown();
            try {
                service.awaitTermination(10, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                LOGGER.error("interrupted while waiting", e);
            }
        }

    }

    private AlertEvent constructAlert(final String id, final LogEvent logEvent, final long duration) {
        final AlertEvent eventAlertEvent = new AlertEvent();
        eventAlertEvent.setId(id);
        eventAlertEvent.setDuration(new Timestamp(duration));
        eventAlertEvent.setAlert(true);
        eventAlertEvent.setHost(logEvent.getHost());
        eventAlertEvent.setLogType(logEvent.getType());
        return eventAlertEvent;
    }
}
