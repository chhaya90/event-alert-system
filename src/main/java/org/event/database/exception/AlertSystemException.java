package org.event.database.exception;

public class AlertSystemException extends Exception {
    private final AlertSystemExceptionCode errorCode;

    public AlertSystemException(final AlertSystemExceptionCode errorCode, final Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode.getErrorCode();
    }

    public String getErrorMessage() {
        return errorCode.getErrorMessage();
    }
}
