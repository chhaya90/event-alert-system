package org.event.database.writer;

import java.sql.SQLException;

import org.event.database.exception.AlertSystemException;
import org.event.dto.AlertEvent;

/**
 * Interface to write to database.
 */
public interface IWriter {
    /**
     * Method create Alert Table in Database.
     * @throws AlertSystemException
     *          this exception will be raised with valid error code if any error occurs while creating table in database.
     */
    void createTable() throws AlertSystemException;

    /**
     * Method to insert alert event.
     * @param event alert event to be written
     * @throws AlertSystemException
     *          this exception will be raised with valid error code if any error occurs while inserting alert event in database.
     */
    void performInsert(AlertEvent event) throws AlertSystemException;

}
