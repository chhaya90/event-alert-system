package org.event.utils;

public final class Constants {
    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS %s (%s varchar(100) NOT NULL, " +
            "%s timestamp, %s varchar(100), %s varchar(100), " +
            "%s BOOLEAN NOT NULL);";
    public static final String INSERT_SQL = "INSERT INTO alert_table(%s) VALUES " +
            "(?,?,?,?,?)";
    public static final String PROPERTIES_FILE_PATH = "/db.properties";
    public static final String RESOURCE_FILE_PATH = "/logfile.txt";
    private Constants() {
    }

}
