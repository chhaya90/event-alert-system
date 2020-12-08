package org.event.main;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.List;
import org.event.Input.FileReader;
import org.event.Input.InputReader;
import org.event.database.exception.AlertSystemException;
import org.event.database.writer.WriteEvent;
import org.event.dto.LogEvent;
import org.event.processor.AlertManager;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Class for integration test of main flow.
 */
public class MainIT {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    public WriteEvent mockWriter;

    @Test
    public void whenApplicationIsInvokedWithValidInput_thenExecutionIsAsExpected() throws AlertSystemException {
        InputReader input = new FileReader("/testLog.txt");
        List<LogEvent> eventLogList = input.read();
        assertThat(eventLogList.size(), is(4));
        AlertManager alertManager = new AlertManager(mockWriter);
        alertManager.createAlert(eventLogList);
        verify(mockWriter, times(1)).performInsert(any());
    }

}
