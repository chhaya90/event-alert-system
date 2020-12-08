package org.event.utils;

public final class AlertDbConstants {
    public static final String ALERT_TABLE = "alert_table";
    public static final String ALERT_ID = "id";
    public static final String DURATION = "duration";
    public static final String LOG_TYPE = "log_type";
    public static final String HOST = "host";
    public static final String ALERT = "alert";

    private AlertDbConstants() {

    }

    /**
     * Returns all column names in format for select query.
     *
     * @return a comma separated list of column names.
     */
    public static String getAllColumnNames() {
        final char comma = ',';
        final StringBuilder columnNames = new StringBuilder();
        return columnNames.append(ALERT_ID).append(comma)
                .append(DURATION).append(comma)
                .append(LOG_TYPE).append(comma)
                .append(HOST).append(comma)
                .append(ALERT).toString();

    }

}
