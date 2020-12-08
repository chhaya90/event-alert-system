package org.event.database.exception;

public enum AlertSystemExceptionCode {

    EVENT_ALERT_PERSISTENCE_ERROR(2000, "Error persisting Event Alert"),
    ALERT_TABLE_CREATE_ERROR(2001, "Error creating Alert Table in database");

    private final int errorCode;
    private final String errorMessage;

    AlertSystemExceptionCode(final int errorCode, final String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
