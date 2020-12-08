package org.event.processor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;
import org.event.database.exception.AlertSystemException;
import org.event.database.writer.WriteEvent;
import org.event.dto.LogEvent;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Unit test class for {@link AlertManager} class.
 */
public class AlertManagerTest {

    public AlertManager objectUnderTest;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    public WriteEvent mockWriter;

    @Before
    public void setup() {
        objectUnderTest = new AlertManager(mockWriter);
    }

    @Test
    public void whenCreateAlertIsCalled_thenExecutionIsSuccessful() throws AlertSystemException {
        final LogEvent LogEventStarted = new LogEventBuilder().setEventId("a").setState("STARTED").setDuration(1491377495213l).build();
        final LogEvent LogEventFinished = new LogEventBuilder().setEventId("a").setState("FINISHED").setDuration(1491377495218l).build();
        final List<LogEvent> logEventList = Arrays.asList(LogEventStarted, LogEventFinished);
        objectUnderTest.createAlert(logEventList);
        verify(mockWriter, times(1)).performInsert(any());

    }

    @Test
    public void whenCreateAlertIsCalledAndExpectedNoEventWritten_thenExecutionIsSuccessful() throws AlertSystemException {
        final LogEvent LogEventStarted = new LogEventBuilder().setEventId("a").setState("STARTED").setDuration(1491377495213l).build();
        final LogEvent LogEventFinished = new LogEventBuilder().setEventId("a").setState("FINISHED").setDuration(1491377495213l).build();
        final List<LogEvent> logEventList = Arrays.asList(LogEventStarted, LogEventFinished);
        objectUnderTest.createAlert(logEventList);
        verify(mockWriter, times(0)).performInsert(any());

    }

}
