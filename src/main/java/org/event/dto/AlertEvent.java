package org.event.dto;

import java.sql.Timestamp;

/**
 * Class to store and represent Alert Event information.
 */
public class AlertEvent {
    private Timestamp duration;
    private String id;
    private String logType;
    private String host;
    private boolean alert;

    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(final Timestamp duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(final String logType) {
        this.logType = logType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(final boolean alert) {
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "Alert{" + "duration=" + duration + ", id='" + id + '\'' + ", log_type='" + logType + '\'' + ", host='" + host + '\'' + ", alert=" + alert + '}';
    }
}
