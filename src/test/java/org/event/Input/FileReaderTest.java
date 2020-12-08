package org.event.Input;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.event.dto.LogEvent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test class for {@link FileReader} class.
 */
public class FileReaderTest {

    @Rule
    public ExpectedException expectedExceptions = ExpectedException.none();

    private InputReader objectUnderTest;

    @Test
    public void whenReadingInputFile_thenRequiredNumberOfRecordIsRead() {
        objectUnderTest = new FileReader("/testLog.txt");
        List<LogEvent> eventLog = objectUnderTest.read();
        assertThat(eventLog.size(), is(4));

    }

    @Test
    public void whenIncorrectFileIsProvided_thenRequiredExceptionIsThrown() throws Exception {
        objectUnderTest = new FileReader("/log.txt");
        expectedExceptions.expect(IllegalArgumentException.class);
        expectedExceptions.expectMessage("InputStream is null for path");
        objectUnderTest.read();

    }

}
