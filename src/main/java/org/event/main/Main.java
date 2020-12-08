package org.event.main;

import static org.event.utils.Constants.RESOURCE_FILE_PATH;
import java.util.List;
import org.event.Input.FileReader;
import org.event.Input.InputReader;
import org.event.database.exception.AlertSystemException;
import org.event.database.writer.IWriter;
import org.event.database.writer.WriteEvent;
import org.event.dto.LogEvent;
import org.event.processor.AlertManager;

public class Main {
    public static void main(String[] args) throws AlertSystemException {
        InputReader input = new FileReader(RESOURCE_FILE_PATH);
        List<LogEvent> eventLogList = input.read();
        IWriter write = new WriteEvent();
        AlertManager alertManager = new AlertManager(write);
        write.createTable();
        alertManager.createAlert(eventLogList);
    }
}
