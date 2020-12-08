package org.event.Input;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.event.dto.Event;
import org.event.dto.LogEvent;
import org.event.processor.EventManager;
import org.event.utils.ResourceLoaderUtils;
import com.google.gson.Gson;

public class FileReader implements InputReader {
    private final String filePath;

    public FileReader(final String filePath) {
        this.filePath = filePath;
    }

    public List<LogEvent> read() {
        Gson g = new Gson();
        final InputStreamReader fileReader = new InputStreamReader(new ResourceLoaderUtils().getClasspathResourceAsStream(filePath));
        LogEvent[] logEvent = g.fromJson(fileReader, LogEvent[].class);
        return Arrays.asList(logEvent);
    }

}
