package org.event.utils;

import static org.event.utils.Constants.PROPERTIES_FILE_PATH;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to load data from specified resources.
 */
public class ResourceLoaderUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceLoaderUtils.class);

    public static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new ResourceLoaderUtils().getClasspathResourceAsStream(PROPERTIES_FILE_PATH)));

        } catch (Exception e) {
            LOGGER.error("Failed to load Database properties from path : {}", PROPERTIES_FILE_PATH, e);
            throw new IOException(e);

        }
        return properties;
    }

    /**
     * Method returns an input stream from given source path.
     *
     * @param path
     *            resource path name
     * @return An input stream for reading the resource, or <tt>null</tt> if the resource could not be found
     */
    public InputStream getClasspathResourceAsStream(String path) {
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        if (inputStream == null) {
            throw new IllegalArgumentException(String.format("InputStream is null for path '%s'", path));
        } else {
            return inputStream;
        }
    }

}
