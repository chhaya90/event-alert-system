package org.event.database.writer;

import static org.event.utils.AlertDbConstants.ALERT;
import static org.event.utils.AlertDbConstants.ALERT_ID;
import static org.event.utils.AlertDbConstants.ALERT_TABLE;
import static org.event.utils.AlertDbConstants.DURATION;
import static org.event.utils.AlertDbConstants.HOST;
import static org.event.utils.AlertDbConstants.LOG_TYPE;
import static org.event.utils.AlertDbConstants.getAllColumnNames;
import static org.event.utils.Constants.CREATE_SQL;
import static org.event.utils.Constants.INSERT_SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.event.database.connection.DbConnection;
import org.event.database.exception.AlertSystemException;
import org.event.database.exception.AlertSystemExceptionCode;
import org.event.dto.AlertEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to write event.
 */
public class WriteEvent implements IWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteEvent.class);

    @Override
    public void createTable() throws AlertSystemException {
        final String query = String.format(CREATE_SQL, ALERT_TABLE, ALERT_ID, DURATION, LOG_TYPE, HOST, ALERT);
        try (Connection connection = DbConnection.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(query);
            }
        } catch (SQLException e) {
            LOGGER.warn("Failed to Execute Create Query : {}", query);
            throw new AlertSystemException(AlertSystemExceptionCode.ALERT_TABLE_CREATE_ERROR, e);
        }
    }

    @Override
    public void performInsert(final AlertEvent alertEvent) throws AlertSystemException {
        final String query = String.format(INSERT_SQL,getAllColumnNames());
        try (Connection connection = DbConnection.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, alertEvent.getId());
                preparedStatement.setTimestamp(2, alertEvent.getDuration());
                preparedStatement.setString(3, alertEvent.getLogType());
                preparedStatement.setString(4, alertEvent.getHost());
                preparedStatement.setBoolean(5, alertEvent.isAlert());
                preparedStatement.execute();
            }
        } catch (final SQLException e) {
            LOGGER.warn("Error persisting event Alert : {}", INSERT_SQL, e);
            throw new AlertSystemException(AlertSystemExceptionCode.EVENT_ALERT_PERSISTENCE_ERROR, e);
        }

    }

}
