package org.event.database.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.event.utils.ResourceLoaderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbConnection.class);
    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        try {
            Properties prop = ResourceLoaderUtils.loadProperties();
            dataSource.setUrl(prop.getProperty("url"));
            dataSource.setUsername(prop.getProperty("username"));
            dataSource.setPassword(prop.getProperty("password"));
            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
            dataSource.setMaxOpenPreparedStatements(10);
        } catch (IOException e) {
            LOGGER.error("Failed to load Database properties", e);
            System.exit(0);
        }
    }

    private DbConnection() {

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
